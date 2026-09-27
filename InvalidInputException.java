package util;

/**
 * Thrown when data entered on a GUI screen fails validation.
 * Caught by the controllers so the app shows a dialog instead of crashing.
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
