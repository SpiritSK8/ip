package myne.command;

import myne.MyneException;
import myne.MyneFace;
import myne.User;

/**
 * A class for exceptions related to improperly formatted commands.
 */
public class InvalidCommandException extends MyneException {
    public InvalidCommandException(String message, MyneFace face, User user) {
        super(message, face, user);
    }
}
