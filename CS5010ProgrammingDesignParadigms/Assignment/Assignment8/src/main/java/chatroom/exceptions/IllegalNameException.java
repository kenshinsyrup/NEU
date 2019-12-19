package chatroom.exceptions;

/**
 * Exception thrown when the user tries to connect to the IM server using an
 * illegal name.
 */
public class IllegalNameException extends RuntimeException {
    /**
     * Generated serial version uid.
     */
    private static final long serialVersionUID = -7330817491637242220L;

    public IllegalNameException(String str) {
        super(str);
    }
}
