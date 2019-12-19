package chatroom.client;

import chatroom.message.Message;
import chatroom.message.MessageType;
import chatroom.util.ChatLogger;
import randomSentenceGenerator.Generator;

import java.util.Scanner;

/**
 * Class which can be used as a command-line IM client.
 */
public class Client {

    /**
     * Main method.
     *
     * @param args Command-line arguments which we ignore
     */
    public static void main(String[] args) {
        IMConnection connect;

        Scanner in = new Scanner(System.in);

        do {
            // Prompt the user to type in a username.
            System.out.println("What username would you like?");

            String username = in.nextLine();

            // Create a Connection to the IM server.
            connect = new IMConnection(args[0], Integer.parseInt(args[1]), username);
        } while (!connect.connect());

        // Create the objects needed to read & write IM messages.
        KeyboardScanner keyboardscanner = connect.getKeyboardScanner();
        MessageScanner messageScanner = connect.getMessageScanner();

        // Repeat the following loop
        while (connect.connectionActive()) {

            // Send part:
            // Check if the user has typed in a line of text, if does, send to the IM server.
            if (keyboardscanner.hasNext()) {
                // Read in the text they typed
                String line = keyboardscanner.nextLine();

                if (line.equals("logoff")) { // logoff: DISCONNECT_MESSAGE 21
                    System.out.println("command: logoff");
                    Message msg = Message.makeQuitMessage(connect.getUserName());
                    connect.sendMessageToSocket(msg);

                } else if (line.startsWith("@all")) { // @all: BROADCAST_MESSAGE 24
                    System.out.println("command: @all");
                    Message msg = Message.makeBroadcastMessage(connect.getUserName(), line.substring(4));
                    connect.sendMessageToSocket(msg);

                } else if (line.startsWith("@")) { // @user: DIRECT_MESSAGE 25
                    System.out.println("command: @");
                    int i = 0;
                    for (char ch : line.toCharArray()) {
                        if (ch == ' ') {
                            break;
                        }
                        i++;
                    }
                    String recipient = line.substring(1, i);
                    Message msg = Message
                            .makeDirecttMessage(connect.getUserName(), recipient, line.substring(i + 1));
                    connect.sendMessageToSocket(msg);

                } else if (line.startsWith("who")) { // who: QUERY_CONNECTED_USERS 22
                    System.out.println("command: who");
                    Message msg = Message.makeQueryMessage(connect.getUserName());
                    connect.sendMessageToSocket(msg);

                } else if (line.startsWith("!")) { // !user: SEND_INSULT 27
                    System.out.println("command: !");
                    // recipient
                    int i = 0;
                    for (char ch : line.toCharArray()) {
                        if (ch == ' ') {
                            break;
                        }
                        i++;
                    }
                    String recipient = line.substring(1, i);
                    System.out.println("recipient: " + recipient);

                    // insult text
                    Generator generator = new Generator("");
                    String insult = "";
                    try {
                        insult = generator.generateOneInsult();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("insult: " + insult);

                    Message msg = Message.makeSendInsultMessage(connect.getUserName(), recipient, insult);
                    connect.sendMessageToSocket(msg);

                } else if (line.equals("?")) { // ?: help manual
                    helpManual();
                }
            }

            // Receive part:
            // Get any recent messages received from the IM server.
            if (messageScanner.hasNext()) {
                Message message = messageScanner.next();
                System.out.println("Received message before further process: " + message);
                System.out.println("connection user name: " + connect.getUserName());

                if (message.getType().equals(MessageType.CONNECT_RESPONSE)) { // CONNECT_RESPONSE 20
                    System.out.println("CONNECT_RESPONSE: " + message);
                    if (message.getName().equals(connect.getUserName())) {
                        if (message.getStatus().equals("true")) {
                            printLog("CONNECT_RESPONSE", "Welcome " + message.getName() + " " + message.getText());
                        } else {
                            printLog("CONNECT_RESPONSE", "Fail to login with name " + message.getName() + ". " + message.getText());
                            System.exit(0);
                        }
                    }

                } else if (message.getType().equals(MessageType.BROADCAST_MESSAGE)) { // BROADCAST_MESSAGE 24
                    System.out.println("BROADCAST_MESSAGE: " + message);
                    if (!message.getName().equals(connect.getUserName())) {
                        printLog("BROADCAST_MESSAGE", message.getName() + ": " + message.getText());
                    }

                } else if (message.getType().equals(MessageType.DIRECT_MESSAGE)) { // DIRECT_MESSAGE 25
                    System.out.println("DIRECT_MESSAGE: " + message);
                    if (message.getRecipient().equals(connect.getUserName())) {
                        printLog("DIRECT_MESSAGE", message.getName() + ": " + message.getText());
                    }

                } else if (message.getType().equals(MessageType.FAILED_MESSAGE)) { // FAILED_MESSAGE 26
                    System.out.println("FAILED_MESSAGE: " + message);
                    if (message.getName().equals(connect.getUserName())) {
                        printLog("FAILED_MESSAGE", "Failed message: " + message.getText());
                    }

                } else if (message.getType().equals(MessageType.QUERY_USER_RESPONSE)) { // QUERY_USER_RESPONSE 23
                    System.out.println("QUERY_USER_RESPONSE: " + message);
                    if (message.getName().equals(connect.getUserName())) {
                        printLog("QUERY_USER_RESPONSE", "QUERY_USER_RESPONSE currently connected users: " + message
                                .getOtherConnectedUsers());
                    }

                } else if (message.getType().equals(MessageType.DISCONNECT_MESSAGE)) { // DISCONNECT_MESSAGE 21
                    System.out.println("DISCONNECT_MESSAGE: " + message);
                    printLog("DISCONNECT_MESSAGE", message.getName() + ": " + message.getText());
                    System.exit(0);

                } else {
                    ChatLogger.error("Wrong message type!");
                }
            }
        }

        System.out.println("Program complete.");
        System.exit(0);
    }

    // Print log with ChatLogger, we need the color.
    private static void printLog(String msgType, String log) {
        ChatLogger.info("New " + msgType + " message-----------");
        ChatLogger.info(log);
        ChatLogger.info("Message done-----------");
    }

    // Most naive way to show help info.
    private static void helpManual() {
        System.out.println("logoff: sends a DISCONNECT_MESSAGE to the server");
        System.out.println("who: sends a QUERY_CONNECTED_USERS to the server");
        System.out.println("@user: sends a DIRECT_MESSAGE to the specified user to the server");
        System.out.println(
                "@all: sends a BROADCAST_MESSAGE to the server, to be sent to all users connected");
        System.out.println(
                "!user: sends a SEND_INSULT message to the server, to be sent to the specified user");
    }
}
