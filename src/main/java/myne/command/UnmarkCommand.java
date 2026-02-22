package myne.command;

import myne.Myne;
import myne.MyneException;
import myne.MyneFace;
import myne.TaskList;
import myne.TaskStorage;
import myne.User;
import myne.task.Task;

/**
 * A class to encapsulate the logic for unmarking a task and parsing the command for doing so.
 */
public class UnmarkCommand implements Command {
    private static final String UNMARK_MESSAGE = "Ah, you would like to redo it? Very well.";
    private static final String USAGE = """
            Usage:
            unmark <task_number>
            unmark <task_name>""";

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
     * @throws InvalidCommandException If the parameters are empty.
     * @throws MyneException If the index provided is less than 1 or more than the task list size.
     */
    @Override
    public Response execute() throws InvalidCommandException, MyneException {
        if (taskList.isEmpty()) {
            throw new MyneException(
                    "Oh my! It seems like you have no tasks at the moment. Shall we remedy that?\n\n",
                    MyneFace.MYNE_WONDER, User.MYNE);
        }

        if (parameters.isBlank()) {
            throw new InvalidCommandException(
                    "I cannot unmark nothing, can I?\n\n" + USAGE, MyneFace.MYNE_DISGUSTED, User.MYNE);
        }

        // If the parameter is an integer, then we unmark by task index.
        if (CommandParser.isNumeric(parameters)) {
            return unmarkByIndex();
        }

        /* If the parameter is not an integer, we treat it as find-then-unmark.
         * If exact matches are found, then we unmark all of them.
         * Otherwise, for partial matches, there must be exactly 1 task that was found.
         * We don't want the user to type "unmark a" and accidentally unmark half of their tasks.
         */

        // Try to find exact matches first
        TaskList findResultExact = taskList.findExact(parameters);
        if (!findResultExact.isEmpty()) {
            return unmarkByExactMatches(findResultExact);
        }

        return unmarkByPartialMatch();
    }

    /**
     * Treats <code>this.parameters</code> as an index, and unmarks the (index - 1) task from <code>this.taskList</code>.
     * @return a response showing which task was unmarked.
     */
    private Response unmarkByIndex() {
        try {
            int index = Integer.parseInt(parameters) - 1;
            taskList.unmark(index);
            storage.saveTasks(taskList);

            return new Response(UNMARK_MESSAGE + "\n\n" + taskList.get(index).toString(),
                    Status.SUCCESS,
                    MyneFace.MYNE_CONFUSED,
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
     * Unmarks all the tasks contained in <code>tl</code> from <code>this.taskList</code> and returns a response.
     * @param tl The <code>TaskList</code> containing the tasks to unmark.
     * @return the response showing which tasks were unmarked.
     */
    private Response unmarkByExactMatches(TaskList tl) {
        StringBuilder sb = new StringBuilder(UNMARK_MESSAGE).append("\n\n");
        for (int i = 0; i < tl.size() - 1; i++) {
            Task taskToUnmark = tl.get(i);
            taskToUnmark.unmark();
            sb.append(taskToUnmark).append("\n");
        }
        Task taskToUnmark = tl.get(tl.size() - 1);
        taskToUnmark.unmark();
        sb.append(taskToUnmark); // Last line doesn't need newline.

        storage.saveTasks(taskList);

        return new Response(sb.toString(),
                Status.SUCCESS,
                MyneFace.MYNE_DEFAULT,
                User.MYNE);
    }

    /**
     * Finds a task from <code>this.taskList</code> that partially match <code>this.parameters</code> and, if exactly
     * 1 task is found, unmarks it.
     * @return a response showing which task was unmarked.
     */
    private Response unmarkByPartialMatch() {
        TaskList findResult = taskList.findMatching(parameters);
        // There must be exactly 1 task to unmark when unmarking by keyword.
        if (findResult.isEmpty()) {
            throw new MyneException("You have no such task.", MyneFace.MYNE_DISGUSTED, User.MYNE);
        }

        if (findResult.size() > 1) {
            throw new MyneException(
                    "More than 1 task match that name. Please be more specific.",
                    MyneFace.MYNE_WORRIED, User.MYNE);
        }

        // Unmark the sole task that was found.
        Task taskToUnmark = findResult.get(0);
        taskToUnmark.unmark();
        storage.saveTasks(taskList);

        return new Response(UNMARK_MESSAGE + "\n\n" + taskToUnmark.toString(),
                Status.SUCCESS,
                MyneFace.MYNE_DEFAULT,
                User.MYNE);
    }
}
