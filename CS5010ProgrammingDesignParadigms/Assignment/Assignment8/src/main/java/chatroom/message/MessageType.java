package chatroom.message;

/**
 * Enumeration for the different types of messages.
 */
public enum MessageType {
    /**
     * Message sent by the user attempting to login using a specified username.
     */
    CONNECT_MESSAGE("19"),

    /**
     * Message sent by the user to start the logging out process and sent by the
     * server once the logout process completes.
     */
    CONNECT_RESPONSE("20"),

    DISCONNECT_MESSAGE("21"),

    QUERY_CONNECTED_USERS("22"),

    QUERY_USER_RESPONSE("23"),

    /**
     * Message whose contents is broadcast to all connected users.
     */
    BROADCAST_MESSAGE("24"),

    DIRECT_MESSAGE("25"),

    FAILED_MESSAGE("26"),

    SEND_INSULT("27");

    /**
     * Store the short name of this message type.
     */
    private String abbreviation;

    /**
     * Define the message type and specify its short name.
     *
     * @param abbrev Short name of this message type, as a String.
     */
    private MessageType(String abbrev) {
        abbreviation = abbrev;
    }

    /**
     * Return a representation of this Message as a String.
     *
     * @return Three letter abbreviation for this type of message.
     */
    @Override
    public String toString() {
        return abbreviation;
    }
}
