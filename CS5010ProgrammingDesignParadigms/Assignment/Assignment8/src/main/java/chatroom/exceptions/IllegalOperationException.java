package chatroom.exceptions;

/**
 * Exception thrown when the user tries to perform an operation that is
 * impossible because of the current state of the program.
 */
public class IllegalOperationException extends RuntimeException {
    /**
     * Generated serial version uid.
     */
    private static final long serialVersionUID = 523551170943325306L;

    public IllegalOperationException(String str) {
        super(str);
    }
}
