package myne.command;

import myne.Myne;
import myne.MyneUi;

/**
 * A class to encapsulate the logic for stopping Myne.
 */
public class ExitCommand implements Command {
    private final Myne myne;
    private final MyneUi ui;

    /**
     * Creates a command that, when calling <code>execute()</code>, will stop the Myne instance.
     * @param myne Instance of Myne.
     */
    public ExitCommand(Myne myne) {
        this.myne = myne;
        this.ui = myne.getUi();
    }

    /**
     * Stops the Myne instance.
     */
    @Override
    public Response execute() {
        myne.exit();
        return new Response(ui.getFarewellText(), Status.SUCCESS);
    }
}
