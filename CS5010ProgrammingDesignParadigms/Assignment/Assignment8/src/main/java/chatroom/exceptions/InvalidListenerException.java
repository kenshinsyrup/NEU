package chatroom.exceptions;

/**
 * Exception thrown when the user tries to use a connection that is not active.
 */
public class InvalidListenerException extends RuntimeException {
    /**
     * Generated serial version uid.
     */
    private static final long serialVersionUID = 5403554498373755882L;

    public InvalidListenerException(String str) {
        super(str);
    }
}
