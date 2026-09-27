package util;

/**
 * Thrown when data entered on a GUI screen fails validation.
 * Caught by the controllers and the message is shown in red on the screen instead of the app crashing.
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
