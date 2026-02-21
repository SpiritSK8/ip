package myne.command;

import myne.FerMyneException;
import myne.FerMyneFace;
import myne.Myne;
import myne.TaskList;
import myne.TaskStorage;
import myne.task.Task;

/**
 * A class to encapsulate the logic for marking a task and parsing the command for doing so.
 */
public class MarkCommand implements Command {
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
     * @throws InvalidCommandException If the index provided is not an integer.
     * @throws IndexOutOfBoundsException If the index provided is less than 1 or more than the task list size.
     */
    @Override
    public Response execute() throws InvalidCommandException, IndexOutOfBoundsException {
        if (parameters.trim().isEmpty()) {
            throw new InvalidCommandException("Where is your task?", FerMyneFace.FERDINAND_EXASPERATED);
        }

        if (CommandParser.isNumeric(parameters)) {
            return markByIndex();
        } else {
            return markByKeyword();
        }
    }

    // This method must be used only if the parameter is guaranteed to be an integer.
    private Response markByIndex() {
        try {
            int index = Integer.parseInt(parameters) - 1;
            taskList.mark(index);
            storage.saveTasks(taskList);

            return new Response("You have carried out your task with utmost diligence. Very good.\n\n"
                    + taskList.get(index).toString(),
                    Status.SUCCESS,
                    FerMyneFace.FERDINAND_HAPPY);

        } catch (IndexOutOfBoundsException e) {
            throw new FerMyneException("I do not recall giving you that task. You only have tasks 1 to "
                    + taskList.size() + ".",
                    FerMyneFace.FERDINAND_DEFAULT);
        }
    }

    private Response markByKeyword() {
        TaskList findResult = taskList.find(parameters);

        // There must be exactly 1 task to mark when marking by keyword.
        if (findResult.isEmpty()) {
            throw new FerMyneException("You have no such task.", FerMyneFace.FERDINAND_EXASPERATED);
        }
        if (findResult.size() > 1) {
            throw new FerMyneException("Which task? Be more specific.", FerMyneFace.FERDINAND_DEFAULT);
        }

        Task taskToMark = findResult.get(0);
        taskToMark.mark();

        return new Response("You have carried out your task with utmost diligence. Very good.\n\n"
                + taskToMark.toString(),
                Status.SUCCESS,
                FerMyneFace.FERDINAND_HAPPY);
    }
}
