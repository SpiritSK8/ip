package myne.command;

import myne.MyneFace;
import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.User;

/**
 * A class that encapsulates the logic for finding tasks and parsing the command for it.
 */
public class FindCommand implements Command {
    private final static String USAGE = """
            Usage:
            find <task_name>""";

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
            throw new InvalidCommandException("You forgot to tell me what to find, fool.\n\n" + USAGE,
                    MyneFace.FERDINAND_EXASPERATED,
                    User.FERDINAND);
        }

        TaskList tasksFound = taskList.findMatching(parameters);

        if (tasksFound.isEmpty()) {
            return new Response("I could not find anything of the sort.",
                    Status.SUCCESS,
                    MyneFace.FERDINAND_EXASPERATED,
                    User.FERDINAND);
        }

        String text = ui.getTaskListText(tasksFound, "Here.");
        return new Response(text, Status.SUCCESS, MyneFace.FERDINAND_DEFAULT, User.FERDINAND);
    }
}
