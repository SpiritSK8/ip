package myne.command;

import myne.FerMyneException;
import myne.FerMyneFace;

/**
 * A class for exceptions related to improperly formatted commands.
 */
public class InvalidCommandException extends FerMyneException {
    public InvalidCommandException(String message, FerMyneFace face) {
        super(message, face);
    }
}
