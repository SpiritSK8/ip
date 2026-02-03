package myne.command;

public interface Command {
    void execute() throws InvalidCommandException;
}
