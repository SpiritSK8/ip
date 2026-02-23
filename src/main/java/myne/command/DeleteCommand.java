package myne.command;

import myne.Myne;
import myne.MyneException;
import myne.MyneFace;
import myne.TaskList;
import myne.TaskStorage;
import myne.User;
import myne.task.Task;

/**
 * A class to encapsulate the logic for deleting tasks and parsing the command for doing so.
 */
public class DeleteCommand implements Command {
    private static final String DELETE_MESSAGE = "Let me give this task to someone else...";
    private static final String USAGE = """
            Usage:
            delete <task_number>
            delete <task_name>""";

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
     * @throws InvalidCommandException If the task index or name are not provided.
     * @throws MyneException If the index provided is less than 1 or more than the task list size.
     */
    @Override
    public Response execute() throws MyneException {
        if (taskList.isEmpty()) {
            throw new MyneException(
                    "Oh my! It seems like you have no tasks at the moment. Shall we remedy that?\n\n",
                    MyneFace.MYNE_WONDER, User.MYNE);
        }

        if (parameters.isBlank()) {
            throw new InvalidCommandException(
                    "Do tell me which task to delete.\n\n" + USAGE, MyneFace.MYNE_CONFUSED, User.MYNE);
        }

        // If the parameter is an integer, then we delete by task index.
        if (CommandParser.isNumeric(parameters)) {
            return deleteByIndex();
        }

        /* If the parameter is not an integer, we treat it as find-then-delete.
         * If exact matches are found, then we delete all of them.
         * Otherwise, for partial matches, there must be exactly 1 task to delete.
         * We don't want the user to type "delete a" and accidentally delete half of their tasks.
         */

        // Try to find exact matches first
        TaskList findResultExact = taskList.findExact(parameters);
        if (!findResultExact.isEmpty()) {
            return deleteByExactMatches(findResultExact);
        }

        return deleteByPartialMatch();
    }

    /**
     * Treats <code>this.parameters</code> as index, and deletes the (index - 1) task from <code>this.taskList</code>.
     * @return a response showing which task was deleted.
     */
    private Response deleteByIndex() throws MyneException {
        try {
            int index = Integer.parseInt(parameters) - 1;
            Task removedTask = taskList.delete(index);
            storage.saveTasks(taskList);

            return new Response(DELETE_MESSAGE + "\n\n" + removedTask,
                    Status.SUCCESS,
                    MyneFace.MYNE_WORRIED,
                    User.MYNE);

        } catch (IndexOutOfBoundsException e) {
            throw new MyneException("Oh my! It seems that you only have tasks 1 to "
                    + taskList.size()
                    + " at present.",
                    MyneFace.MYNE_WONDER,
                    User.MYNE);
        }
    }

    /**
     * Deletes all the tasks contained in <code>tl</code> from <code>this.taskList</code> and returns a response.
     * @param tl The <code>TaskList</code> containing the tasks to delete.
     * @return the response showing which tasks were deleted.
     */
    private Response deleteByExactMatches(TaskList tl) {
        StringBuilder sb = new StringBuilder(DELETE_MESSAGE).append("\n\n");
        for (int i = 0; i < tl.size() - 1; i++) {
            Task taskToDelete = tl.get(i);
            if (taskList.delete(taskToDelete)) {
                sb.append(taskToDelete).append("\n");
            }
        }
        Task taskToDelete = tl.get(tl.size() - 1);
        if (taskList.delete(taskToDelete)) {
            sb.append(taskToDelete); // Last line doesn't need newline.
        }

        storage.saveTasks(taskList);

        return new Response(sb.toString(),
                Status.SUCCESS,
                MyneFace.MYNE_WORRIED,
                User.MYNE);
    }

    /**
     * Finds a task from <code>this.taskList</code> that partially match <code>this.parameters</code> and, if exactly
     * 1 task is found, deletes it.
     * @return a response showing which task was deleted.
     */
    private Response deleteByPartialMatch() throws MyneException {
        TaskList findResult = taskList.findMatching(parameters);
        if (findResult.isEmpty()) {
            throw new MyneException("You have no such task.", MyneFace.MYNE_DISGUSTED, User.MYNE);
        }

        if (findResult.size() > 1) {
            throw new MyneException(
                    "More than 1 task match that name. Please be more specific.",
                    MyneFace.MYNE_WORRIED, User.MYNE);
        }

        // Delete the sole task that was found.
        Task taskToDelete = findResult.get(0);
        taskList.delete(taskToDelete);
        storage.saveTasks(taskList);

        return new Response(DELETE_MESSAGE + "\n\n" + taskToDelete,
                Status.SUCCESS,
                MyneFace.MYNE_WORRIED,
                User.MYNE);
    }
}
