package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.TaskStorage;

/**
 * A class to encapsulate the logic for unmarking a task and parsing the command for doing so.
 */
public class UnmarkCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    /**
     * Creates a command that, when calling <code>execute()</code>, will unmark the specified task from the Myne instance.
     * @param myne Instance of Myne.
     * @param parameters The index of the task, 1-indexed, as a string. For example, "1" will unmark the first task, not the second.
     */
    public UnmarkCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
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
    public void execute() throws InvalidCommandException, IndexOutOfBoundsException {
        try {
            // Unmark task and save.
            int index = Integer.parseInt(parameters) - 1;
            taskList.unmark(index);
            storage.saveTasks(taskList);

            // Show message.
            ui.showMessage("Ah, you would like to redo it? Very well.\n  " + taskList.get(index).toString());
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(parameters + " is not a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Oh my! It seems that you only have "
                    + taskList.size()
                    + " tasks at present.");
        }
    }
}
