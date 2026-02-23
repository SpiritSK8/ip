package myne.command;

import myne.Myne;
import myne.MyneException;
import myne.MyneFace;
import myne.TaskList;
import myne.TaskStorage;
import myne.User;
import myne.task.Task;

/**
 * A class to encapsulate the logic for marking a task and parsing the command for doing so.
 */
public class MarkCommand implements Command {
    private static final String MARK_MESSAGE = "You have carried out your task with utmost diligence. Very good.";
    private static final String USAGE = """
            Usage:
            mark <task_number>
            mark <task_name>
            
            Example:
            mark 1
            mark Read books""";

    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    /**
     * Creates a command that, when calling <code>execute()</code>, will mark the specified task from the Myne instance.
     * @param myne Instance of Myne.
     * @param parameters The index of the task, 1-indexed, as a string.
     *                   For example, "1" will mark the first task, not the second.
     */
    public MarkCommand(Myne myne, String parameters) {
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    /**
     * Marks the task from Myne's task list and saves it to the file.
     * @throws InvalidCommandException If the parameters are empty.
     * @throws MyneException If the index provided is less than 1 or more than the task list size.
     */
    @Override
    public Response execute() throws MyneException {
        if (taskList.isEmpty()) {
            throw new MyneException(
                    "You have no tasks yet. Do not slack off.\n\n",
                    MyneFace.FERDINAND_EXASPERATED, User.FERDINAND);
        }

        if (parameters.isBlank()) {
            throw new InvalidCommandException(
                    "Which task?\n\n" + USAGE, MyneFace.FERDINAND_EXASPERATED, User.FERDINAND);
        }

        if (CommandParser.isNumeric(parameters)) {
            return markByIndex();
        }

        /* If the parameter is not an integer, we treat it as find-then-mark.
         * If exact matches are found, then we mark all of them.
         * Otherwise, for partial matches, there must be exactly 1 task that was found.
         * We don't want the user to type "mark a" and accidentally mark half of their tasks.
         */

        TaskList findResultExact = taskList.findExact(parameters);
        if (!findResultExact.isEmpty()) {
            return markByExactMatches(findResultExact);
        }

        return markByPartialMatch();
    }

    /**
     * Treats <code>this.parameters</code> as an index, and marks the (index - 1) task from <code>this.taskList</code>.
     * @return a response showing which task was marked.
     */
    private Response markByIndex() {
        try {
            int index = Integer.parseInt(parameters) - 1;
            taskList.mark(index);
            storage.saveTasks(taskList);

            return new Response(MARK_MESSAGE + "\n\n" + taskList.get(index).toString(),
                    Status.SUCCESS,
                    MyneFace.FERDINAND_HAPPY,
                    User.FERDINAND);

        } catch (IndexOutOfBoundsException e) {
            throw new MyneException("I do not recall giving you that task. You only have tasks 1 to "
                    + taskList.size() + ".",
                    MyneFace.FERDINAND_DEFAULT,
                    User.FERDINAND);
        }
    }

    /**
     * Marks all the tasks contained in <code>tl</code> from <code>this.taskList</code> and returns a response.
     * @param tl The <code>TaskList</code> containing the tasks to mark.
     * @return the response showing which tasks were marked.
     */
    private Response markByExactMatches(TaskList tl) {
        StringBuilder sb = new StringBuilder(MARK_MESSAGE).append("\n\n");
        for (int i = 0; i < tl.size() - 1; i++) {
            Task taskToMark = tl.get(i);
            taskToMark.mark();
            sb.append(taskToMark).append("\n");
        }
        Task taskToMark = tl.get(tl.size() - 1);
        taskToMark.mark();
        sb.append(taskToMark); // Last line doesn't need newline.

        storage.saveTasks(taskList);

        return new Response(sb.toString(),
                Status.SUCCESS,
                MyneFace.FERDINAND_HAPPY,
                User.FERDINAND);
    }

    /**
     * Finds a task from <code>this.taskList</code> that partially match <code>this.parameters</code> and, if exactly
     * 1 task is found, marks it.
     * @return a response showing which task was marked.
     */
    private Response markByPartialMatch() {
        TaskList findResult = taskList.findMatching(parameters);
        // There must be exactly 1 task to mark when marking by keyword.
        if (findResult.isEmpty()) {
            throw new MyneException(
                    "You have no such task.", MyneFace.FERDINAND_EXASPERATED, User.FERDINAND);
        }
        if (findResult.size() > 1) {
            throw new MyneException(
                    "There are more than 1 task that match that name, fool. Be more specific.",
                    MyneFace.FERDINAND_DEFAULT, User.FERDINAND);
        }

        // Mark the sole task that was found.
        Task taskToMark = findResult.get(0);
        taskToMark.mark();
        storage.saveTasks(taskList);

        return new Response(MARK_MESSAGE + "\n\n" + taskToMark.toString(),
                Status.SUCCESS,
                MyneFace.FERDINAND_HAPPY,
                User.FERDINAND);
    }
}
