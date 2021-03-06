package chatroom.message;

import java.util.ArrayList;
import java.util.List;

/**
 * Each instance of this class represents a single transmission by our IM clients.
 */
public class Message {

    /**
     * The string sent when a field is null.
     */
    private static final String NULL_OUTPUT = "--";

    /**
     * The handle of the message.
     */
    private MessageType msgType;

    /**
     * The first argument used in the message. This will be the sender's identifier.
     */
    private String msgSender;

    /**
     * The second argument used in the message.
     */
    private String msgText;


    // login status
    private String status;

    // recipient
    private String recipient;

    // connected users
    private List<String> otherConnectedUsers;

    /**
     * Create a new message that contains actual IM text. The type of distribution is defined by the
     * handle and we must also set the name of the message sender, message recipient, and the text to
     * send.
     *
     * @param handle  Handle for the type of message being created.
     * @param srcName Name of the individual sending this message
     * @param text    Text of the instant message
     */
    private Message(MessageType handle, String srcName, String text) {
        msgType = handle;
        // Save the properly formatted identifier for the user sending the
        // message.
        msgSender = srcName;
        // Save the text of the message.
        msgText = text;
    }

    /**
     * Create a new message that contains a command sent the server that requires a single argument.
     * This message contains the given handle and the single argument.
     *
     * @param handle  Handle for the type of message being created.
     * @param srcName Argument for the message; at present this is the name used to log-in to the IM
     *                server.
     */
    private Message(MessageType handle, String srcName) {
        this(handle, srcName, null);
    }

    /**
     * Create a new message stating the name with which the user would like to login.
     *
     * @param text Name the user wishes to use as their screen name.
     * @return Instance of Message that can be sent to the server to try and login.
     */
    public static Message makeConnectMessage(String myName, String text) {
        return new Message(MessageType.CONNECT_MESSAGE, myName, text);
    }

    public static Message makeConnectResponse(String myName, String status, String text) {
        Message msg = new Message(MessageType.CONNECT_RESPONSE, myName, text);
        msg.status = status;
        return msg;
    }

    public static Message makeDirecttMessage(String myName, String recipient, String text) {
        Message msg = new Message(MessageType.DIRECT_MESSAGE, myName, text);
        msg.recipient = recipient;
        return msg;
    }

    public static Message makeFailedMessage(String myName, String text) {
        return new Message(MessageType.FAILED_MESSAGE, myName, text);
    }

    public static Message makeQueryMessage(String myName) {
        Message msg = new Message(MessageType.QUERY_CONNECTED_USERS, myName, "");
        return msg;
    }

    public static Message makeQueryResponseMessage(String myName, List<String> otherConnectedUsers) {
        Message msg = new Message(MessageType.QUERY_USER_RESPONSE, myName, "");
        msg.otherConnectedUsers = new ArrayList<>(otherConnectedUsers);
        return msg;
    }

    public static Message makeSendInsultMessage(String myName, String recipient, String text) {
        Message msg = new Message(MessageType.SEND_INSULT, myName, text);
        msg.recipient = recipient;
        return msg;
    }

    /**
     * Create a new message broadcasting an announcement to the world.
     *
     * @param myName Name of the sender of this very important missive.
     * @param text   Text of the message that will be sent to all users
     * @return Instance of Message that transmits text to all logged in users.
     */
    public static Message makeBroadcastMessage(String myName, String text) {
        return new Message(MessageType.BROADCAST_MESSAGE, myName, text);
    }


    /**
     * Create a new message to continue the logout process.
     *
     * @param myName The name of the client that sent the quit message.
     * @return Instance of Message that specifies the process is logging out.
     */
    public static Message makeQuitMessage(String myName) {
        return new Message(MessageType.DISCONNECT_MESSAGE, myName, null);
    }

    /**
     * Given a handle, name and text, return the appropriate message instance or an instance from a
     * subclass of message.
     *
     * @param handle  Handle of the message to be generated.
     * @param srcName Name of the originator of the message (may be null)
     * @param text    Text sent in this message (may be null)
     * @return Instance of Message (or its subclasses) representing the handle, name, & text.
     */
    public static Message makeMessage(String handle, String srcName, String recipient, String status, List<String> otherConnectedUsers, String text) {
        Message result = null;

        if (handle.equals(MessageType.CONNECT_MESSAGE.toString())) {
            result = makeSimpleLoginMessage(srcName);

        } else if (handle.equals(MessageType.CONNECT_RESPONSE.toString())) {
            result = makeConnectResponse(srcName, status, text);

        } else if (handle.equals(MessageType.DISCONNECT_MESSAGE.toString())) {
            result = makeQuitMessage(srcName);

        } else if (handle.equals(MessageType.QUERY_CONNECTED_USERS.toString())) {
            result = makeQueryMessage(srcName);

        } else if (handle.equals(MessageType.QUERY_USER_RESPONSE.toString())) {
            result = makeQueryResponseMessage(srcName, otherConnectedUsers);

        } else if (handle.equals(MessageType.BROADCAST_MESSAGE.toString())) {
            result = makeBroadcastMessage(srcName, text);

        } else if (handle.equals(MessageType.DIRECT_MESSAGE.toString())) {
            result = makeDirecttMessage(srcName, recipient, text);

        } else if (handle.equals(MessageType.FAILED_MESSAGE.toString())) {
            result = makeFailedMessage(srcName, text);

        } else if (handle.equals(MessageType.SEND_INSULT.toString())) {
            result = makeSendInsultMessage(srcName, recipient, text);
        }

        System.out.println("result msg: " + result);
        return result;
    }

    /**
     * Create a new message for the early stages when the user logs in without all the special stuff.
     *
     * @param myName Name of the user who has just logged in.
     * @return Instance of Message specifying a new friend has just logged in.
     */
    public static Message makeSimpleLoginMessage(String myName) {
        return new Message(MessageType.CONNECT_MESSAGE, myName);
    }

    /**
     * Return the name of the sender of this message.
     *
     * @return String specifying the name of the message originator.
     */
    public String getName() {
        return msgSender;
    }

    /**
     * Return the type of this message.
     *
     * @return MessageType for this message.
     */
    public MessageType getType() {
        return msgType;
    }

    /**
     * Return the text of this message.
     *
     * @return String equal to the text sent by this message.
     */
    public String getText() {
        return msgText;
    }

    /**
     * Determine if this message is broadcasting text to everyone.
     *
     * @return True if the message is a broadcast message; false otherwise.
     */
    public boolean isBroadcastMessage() {
        return (msgType == MessageType.BROADCAST_MESSAGE);
    }

    public boolean isDirectMessage() {
        return (msgType == MessageType.DIRECT_MESSAGE);
    }

    public boolean isQueryMessage() {
        return (msgType == MessageType.QUERY_CONNECTED_USERS);
    }

    public boolean isSendInsultMessage() {
        return (msgType == MessageType.SEND_INSULT);
    }

    /**
     * Determine if this message is sent by a new client to log-in to the server.
     *
     * @return True if the message is an initialization message; false otherwise
     */
    public boolean isInitialization() {
        return (msgType == MessageType.CONNECT_MESSAGE);
    }

    /**
     * Determine if this message is a message signing off from the IM server.
     *
     * @return True if the message is sent when signing off; false otherwise
     */
    public boolean terminate() {
        return (msgType == MessageType.DISCONNECT_MESSAGE);
    }

    /**
     * Representation of this message as a String. This begins with the message handle and then
     * contains the length (as an integer) and the value of the next two arguments.
     *
     * @return Representation of this message as a String.
     */
    @Override
    public String toString() {
        String result = msgType.toString();

        if (msgSender != null) {
            result += " " + msgSender.length() + " " + msgSender;
        } else {
            result += " " + NULL_OUTPUT.length() + " " + NULL_OUTPUT;
        }

        if (msgType.equals(MessageType.CONNECT_RESPONSE)) {
            result += " " + status.length() + " " + status;
        }

        if (msgType.equals((MessageType.DIRECT_MESSAGE)) || (msgType.equals(MessageType.SEND_INSULT))) {
            result += " " + recipient.length() + " " + recipient;
        }

        if (msgType.equals(MessageType.QUERY_USER_RESPONSE)) {
            if (otherConnectedUsers != null && otherConnectedUsers.size() != 0) {
                result += " " + otherConnectedUsers.size();

                for (String name : otherConnectedUsers) {
                    result += " " + name.length() + " " + name;
                }
            } else {
                result += "0";
            }
        }

        if (msgText != null) {
            result += " " + msgText.length() + " " + msgText;
        } else {
            result += " " + NULL_OUTPUT.length() + " " + NULL_OUTPUT;
        }
        return result;
    }

    public String getStatus() {
        return status;
    }

    public String getRecipient() {
        return recipient;
    }

    public List<String> getOtherConnectedUsers() {
        return otherConnectedUsers;
    }
}
