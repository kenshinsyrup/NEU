package chatroom.client;

import chatroom.exceptions.IllegalNameException;
import chatroom.exceptions.IllegalOperationException;
import chatroom.exceptions.InvalidListenerException;
import chatroom.message.Message;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * This class manages the connection between an the IM client and the IM server.
 * Instances of this class can be relied upon to manage all the details of this
 * connection and sends alerts when appropriate. Instances of this class must be
 * constructed and connected before it can be used to transmit messages.
 */
public class IMConnection {

    /**
     * Real Connection instance which this class wraps and makes presentable to the
     * user
     */
    private SocketNB socketConnection;

    /**
     * List of instances that have registered as a listener for received message
     * events.
     */
    private Vector<MessageListener> messageListeners;

    /**
     * Server to which this connection will be made.
     */
    private String hostName;

    /**
     * Port to which this connection will be made.
     */
    private int portNum;

    /**
     * Name of the user for which this connection was formed.
     */
    private String userName;

    /**
     * Holds the SwingWorker which is used to read and process all incoming data.
     */
    private SwingWorker<Void, Message> workerBee;

    /**
     * Instance used to read the messages.
     */
    private MessageScanner messageScanner;

    /**
     * Creates an instance that will manage a connection with an IM server, but does
     * not begin the process of making a connection to the IM server.
     *
     * @param host     The name of the host that this connection is using
     * @param port     The port number to use.
     * @param username Name of the user for which this connection is being made.
     */
    public IMConnection(String host, int port, String username) {
        if ((username == null) || username.trim().equals("")) {
            username = "DumbUser";
        }

        messageListeners = new Vector<MessageListener>();
        userName = username;
        hostName = host;
        portNum = port;
    }

    /**
     * Add the given listener to be notified whenever 1 or more Messages are
     * received from IM server via this connection.
     *
     * @param listener Instance which will begin to receive notifications of any
     *                 messages received by this IMConnection.
     * @throws InvalidListenerException Exception thrown when this is called with a
     *                                  value of null for {@code listener}
     */
    public void addMessageListener(MessageListener listener) {
        if (listener == null) {
            throw new InvalidListenerException("Cannot add (null) as a listener!");
        }

        messageListeners.add(listener);
    }

    /**
     * Send a message to log in to the IM server using the given username.
     * Precondition: connectionActive() == false
     *
     * @return True if the connection was successfully made; false otherwise.
     * @throws IllegalNameException Exception thrown if we try to connect with an
     *                              illegal username. Legal usernames can only
     *                              contain letters and numbers.
     */
    public boolean connect() {
        boolean retVal = login();

        MessageScanner rms = MessageScanner.getInstance();
        addMessageListener(rms);
        messageScanner = rms;

        return retVal;
    }

    /**
     * Returns whether the instance is managing an active, logged-in connection
     * between the client and an IM server.
     *
     * @return True if the client is logged in to the server using this connection;
     * false otherwise.
     */
    public boolean connectionActive() {
        if (socketConnection == null) {
            return false;
        } else {
            return socketConnection.isConnected();
        }
    }

    /**
     * Gets an object which can be used to read what the user types in on the
     * keyboard without waiting. The object returned by this method should be used
     * rather than {@link Scanner} since {@code Scanner} will cause a program to
     * halt if there is no input.
     *
     * @return Instance of {@link KeyboardScanner} that can be used to read keyboard
     * input for this connection of the server.
     */
    public KeyboardScanner getKeyboardScanner() {
        return KeyboardScanner.getInstance();
    }

    /**
     * Gets an object which can be used to get the message sent by the server over
     * this connection. This is the only object that can be used to retrieve all
     * these messages.
     *
     * @return Instance of {@link MessageScanner} that can be used to read message
     * sent over this connection for this user.
     */
    public MessageScanner getMessageScanner() {
        if (messageScanner == null) {
            throw new IllegalOperationException("Cannot get a MessageScanner if you have not connected to the server!");
        }

        return messageScanner;
    }

    /**
     * Get the name of the user for which we have created this connection.
     *
     * @return Current value of the user name and/or the username with which we
     * logged in to this IM server.
     */
    public String getUserName() {
        return userName;
    }

    public void sendMessageToSocket(Message msg) {
        if (!connectionActive()) {
            throw new IllegalOperationException("Cannot send a message if you are not connected to a server!\n");
        }

        socketConnection.send(msg);
    }

    /**
     * Send a message to log in to the IM server using the given username. For the
     * moment, you will automatically be logged in to the server, even if there is
     * already someone with that username.<br/>
     * Precondition: connectionActive() == false
     *
     * @return True if the connection was successfully made; false otherwise.
     */
    private boolean login() {
        // Now log in using this name.
        Message loginMessage = Message.makeConnectMessage(userName, "login");

        try {
            socketConnection = new SocketNB(hostName, portNum);
            socketConnection.startIMConnection();
        } catch (IOException e) {
            // Report the error
            System.err.println("ERROR:  Could not make a connection to: " + hostName + " at port " + portNum);
            System.err.println(
                    "        If the settings look correct and your machine is connected to the Internet, report this error to Dr. Jump");
            // And print out the problem
            e.printStackTrace();
            // Return that the connection could not be made.
            return false;
        }

        System.out.println("Socket is connected.");

        // Send the message to log us into the system.
        socketConnection.send(loginMessage);

        // Create the background thread that handles our incoming messages.
        workerBee = new ScanForMessagesWorker(this, socketConnection);
        // Start the worker bee scanning for messages.
        workerBee.execute();

        // Return that we were successful
        return true;
    }

    @SuppressWarnings({"unchecked"})
    protected void fireSendMessages(List<Message> mess) {
        Vector<MessageListener> targets;
        synchronized (this) {
            targets = (Vector<MessageListener>) messageListeners.clone();
        }
        for (MessageListener iml : targets) {
            iml.messagesReceived(mess.iterator());
        }
    }

    protected void loggedOut() {
        socketConnection = null;
    }
}