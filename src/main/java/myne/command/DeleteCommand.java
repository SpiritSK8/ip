package myne.command;

import myne.FerMyneException;
import myne.FerMyneFace;
import myne.Myne;
import myne.TaskList;
import myne.TaskStorage;
import myne.task.Task;

/**
 * A class to encapsulate the logic for deleting tasks and parsing the command for doing so.
 */
public class DeleteCommand implements Command {
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    /**
     * Creates a command that, when calling <code>execute()</code>,
     * will delete the specified task from the Myne instance.
     * @param myne Instance of Myne.
     * @param parameters The index of the task, 1-indexed, as a string.
     *                   For example, "1" will delete the first task, not the second.
     */
    public DeleteCommand(Myne myne, String parameters) {
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    /**
     * Deletes the task from Myne's task list and updates the file.
     * @throws InvalidCommandException If the index provided is not an integer.
     * @throws IndexOutOfBoundsException If the index provided is less than 1 or more than the task list size.
     */
    @Override
    public Response execute() throws InvalidCommandException, IndexOutOfBoundsException {
        if (parameters.isBlank()) {
            throw new InvalidCommandException("Do tell me which task to delete.", FerMyneFace.MYNE_CONFUSED);
        }

        if (CommandParser.isNumeric(parameters)) {
            return deleteByIndex();
        } else {
            return deleteByKeyword();
        }
    }

    // This method must be used only if the parameter is guaranteed to be an integer.
    private Response deleteByIndex() {
        try {
            int index = Integer.parseInt(parameters) - 1;
            Task removedTask = taskList.delete(index);
            storage.saveTasks(taskList);

            return new Response("Let me take that back.\n\n" + removedTask,
                    Status.SUCCESS,
                    FerMyneFace.MYNE_HAPPY);

        } catch (IndexOutOfBoundsException e) {
            throw new FerMyneException("Oh my! It seems that you only have tasks 1 to "
                    + taskList.size()
                    + " at present.",
                    FerMyneFace.MYNE_CONFUSED);
        }
    }

    private Response deleteByKeyword() {
        TaskList findResult = taskList.find(parameters);

        // There must be exactly 1 task to delete when deleting by keyword.
        if (findResult.isEmpty()) {
            throw new InvalidCommandException("You have no such task.", FerMyneFace.MYNE_DISGUSTED);
        }
        if (findResult.size() > 1) {
            throw new InvalidCommandException("Which task? Please be more specific.", FerMyneFace.MYNE_WORRIED);
        }

        Task taskToDelete = findResult.get(0);
        taskList.delete(taskToDelete);
        storage.saveTasks(taskList);

        return new Response("Let me take that back.\n\n" + taskToDelete,
                Status.SUCCESS,
                FerMyneFace.MYNE_HAPPY);
    }
}
