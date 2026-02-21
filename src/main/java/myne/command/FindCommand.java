package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;

/**
 * A class that encapsulates the logic for finding tasks and parsing the command for it.
 */
public class FindCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final String parameters;

    /**
     * Creates a command that, when calling <code>execute()</code>, will find the tasks containing the specified text
     * from the Myne instance.
     * @param myne Instance of Myne.
     * @param parameters The text to find.
     */
    public FindCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.parameters = parameters;
    }

    /**
     * Finds the tasks that contain the specified text.
     * @throws InvalidCommandException If <code>parameters</code> is empty.
     */
    @Override
    public Response execute() throws InvalidCommandException {
        if (parameters.isEmpty()) {
            // Cannot find empty string.
            throw new InvalidCommandException("I cannot search anything if you do not tell me what to find.");
        }

        TaskList tasksFound = taskList.find(parameters);

        if (tasksFound.isEmpty()) {
            return new Response("I could not find anything of the sort.", Status.SUCCESS);
        }

        String text = ui.getTaskListText(tasksFound, "Here are what I found from the archive.");
        return new Response(text, Status.SUCCESS);
    }
}
