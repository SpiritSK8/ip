package myne.command;

import myne.InvalidCommandException;

public interface Command {
    void execute() throws InvalidCommandException;
}
