package myne.command;

import myne.FerMyneException;
import myne.FerMyneFace;
import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.TaskStorage;
import myne.task.Task;

/**
 * A class to encapsulate the logic for unmarking a task and parsing the command for doing so.
 */
public class UnmarkCommand implements Command {
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    /**
     * Creates a command that, when calling <code>execute()</code>,
     * will unmark the specified task from the Myne instance.
     * @param myne Instance of Myne.
     * @param parameters The index of the task, 1-indexed, as a string.
     *                   For example, "1" will unmark the first task, not the second.
     */
    public UnmarkCommand(Myne myne, String parameters) {
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    /**
     * Unmarks the task from Myne's task list and saves it to the file.
     * @throws InvalidCommandException If the index provided is not an integer.
     * @throws IndexOutOfBoundsException If the index provided is less than 1 or more than the task list size.
     */
    @Override
    public Response execute() throws InvalidCommandException, IndexOutOfBoundsException {
        if (parameters.trim().isEmpty()) {
            throw new InvalidCommandException("I cannot unmark nothing.", FerMyneFace.MYNE_DISGUSTED);
        }

        if (CommandParser.isNumeric(parameters)) {
            return unmarkByIndex();
        } else {
            return unmarkByKeyword();
        }
    }

    // This method must be used only if the parameter is guaranteed to be an integer.
    private Response unmarkByIndex() {
        try {
            int index = Integer.parseInt(parameters) - 1;
            taskList.mark(index);
            storage.saveTasks(taskList);

            return new Response("Ah, you would like to redo it? Very well.\n\n" + taskList.get(index).toString(),
                    Status.SUCCESS,
                    FerMyneFace.MYNE_DEFAULT);

        } catch (IndexOutOfBoundsException e) {
            throw new FerMyneException("Oh my! It seems that you only have tasks 1 to "
                    + taskList.size()
                    + " at present.",
                    FerMyneFace.MYNE_CONFUSED);
        }
    }

    private Response unmarkByKeyword() {
        TaskList findResult = taskList.find(parameters);

        // There must be exactly 1 task to unmark when unmarking by keyword.
        if (findResult.isEmpty()) {
            throw new InvalidCommandException("You have no such task.", FerMyneFace.MYNE_WORRIED);
        }
        if (findResult.size() > 1) {
            throw new InvalidCommandException("Which task? Please be more specific.", FerMyneFace.MYNE_WORRIED);
        }

        Task taskToUnmark = findResult.get(0);
        taskToUnmark.unmark();

        return new Response("Ah, you would like to redo it? Very well.\n\n" + taskToUnmark.toString(),
                Status.SUCCESS,
                FerMyneFace.MYNE_DEFAULT);
    }
}
