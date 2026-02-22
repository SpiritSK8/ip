package myne.command;

import myne.MyneException;
import myne.MyneFace;

/**
 * A class for exceptions related to improperly formatted commands.
 */
public class InvalidCommandException extends MyneException {
    public InvalidCommandException(String message, MyneFace face, String name) {
        super(message, face, name);
    }
}
