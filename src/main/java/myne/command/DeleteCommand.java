package myne.command;

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
        if (parameters.trim().isEmpty()) {
            throw new InvalidCommandException("I cannot delete something that does not exist.");
        }

        try {
            // Delete task and save.
            int index = Integer.parseInt(parameters) - 1;
            Task removedTask = taskList.delete(index);
            storage.saveTasks(taskList);

            return new Response("Let me take that back.\n" + removedTask, Status.SUCCESS);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(parameters + " is not a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Oh my! It seems that you only have "
                    + taskList.size()
                    + " tasks at present.");
        }
    }
}
