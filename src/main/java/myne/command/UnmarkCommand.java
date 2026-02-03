package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.TaskStorage;

import myne.InvalidCommandException;

public class UnmarkCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    public UnmarkCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
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
            throw new InvalidCommandException("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
        }
    }
}
