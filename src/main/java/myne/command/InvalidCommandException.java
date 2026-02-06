package myne.command;

/**
 * A class for exceptions related to improperly formatted commands.
 */
public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
