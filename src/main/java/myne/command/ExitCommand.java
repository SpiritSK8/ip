package myne.command;

import myne.Myne;

/**
 * A class to encapsulate the logic for stopping Myne.
 */
public class ExitCommand implements Command {
    private final Myne myne;

    /**
     * Creates a command that, when calling <code>execute()</code>, will stop the Myne instance.
     * @param myne Instance of Myne.
     */
    public ExitCommand(Myne myne) {
        this.myne = myne;
    }

    /**
     * Stops the Myne instance.
     */
    @Override
    public void execute() {
        myne.exit();
    }
}
