package myne.command;

/**
 * An interface for Myne commands. The parameters for each command are passed through its constructor rather
 * than through the <code>execute()</code> method.
 */
public interface Command {
    /**
     * Executes the command and returns a <code>Response</code>.
     * @throws InvalidCommandException If the command parameters do not follow the expected format.
     */
    Response execute() throws InvalidCommandException;
}
