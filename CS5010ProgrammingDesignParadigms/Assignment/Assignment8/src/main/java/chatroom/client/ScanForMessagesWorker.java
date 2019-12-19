package chatroom.client;

import chatroom.message.Message;
import chatroom.message.MessageType;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class that continuously scans for incoming messages. Because of the CPU overhead that this
 * entails, we place this work off onto a separate worker thread. This has the side effect of not
 * interfering with the event dispatch thread. Like all <code>SwingWorker</code>s each instance
 * should be executed exactly once.
 */
public final class ScanForMessagesWorker extends SwingWorker<Void, Message> {

    /**
     * IM connection instance for which we are working.
     */
    private final IMConnection imConnection;

    /**
     * List holding all messages we have read and not yet pushed out to the user.
     */
    private List<Message> messagesStoreage;

    /**
     * Instance which actually performs the low-level I/O with the server.
     */
    private SocketNB realConnection;

    /**
     * Create an initialize the worker thread we will use to scan for incoming messages.
     *
     * @param cimConnection Instance to which this will be attached.
     * @param sock          Socket instance which really hosts the connection to the server
     */
    ScanForMessagesWorker(IMConnection cimConnection, SocketNB sock) {
        // Record the instance and connection we will be using
        imConnection = cimConnection;
        realConnection = sock;

        // Create the queue that will hold the messages received from over the network.
        messagesStoreage = new CopyOnWriteArrayList<Message>();
    }

    /**
     * Executes the steps needed to switch which method is being executed.
     *
     * @return Null value needed to comply with original method definition
     */
    @Override
    protected Void doInBackground() {
        while (!isCancelled()) {
            // blocking op, read the message from the socket connection, put it into the messagesStoreage
            realConnection.enqueueMessages(messagesStoreage);

            if (!messagesStoreage.isEmpty()) {

                // Add this message into our queue
                /**
                 * Sends data chunks to the process(java.util.List) method. This method is to be used from inside the
                 * doInBackground method to deliver intermediate results for processing on the Event Dispatch Thread
                 * inside the process method.
                 */
                publish(messagesStoreage.remove(0));
            }
        }
        return null;
    }

    @Override
    protected void process(List<Message> messages) {
        List<Message> publishList = new LinkedList<Message>();
        boolean flagForClosure = false;

        for (Message m : messages) {

            System.out.println("process m:" + m);

            /**
             * If DISCONNECT_MESSAGE, we make the flagForClosure true. Otherwise, we push the msg to the publishList,
             * then check if msg is a Bad CONNECT_RESPONSE, we make the flagForClosure true.
             */
            if (m.getType().equals(MessageType.DISCONNECT_MESSAGE)) {
                flagForClosure = true;
            } else {
                publishList.add(m);

                if (m.getType().equals(MessageType.CONNECT_RESPONSE) && !m.getStatus().equals("true")) {
                    flagForClosure = true;
                }
            }
        }

        // Publish the messages to MessageScanner.
        if (!publishList.isEmpty()) {
            imConnection.fireSendMessages(publishList);
        }

        // if flagForClosure is true, disconnect
        if (flagForClosure) {
            cancel(false);
        }
    }
}