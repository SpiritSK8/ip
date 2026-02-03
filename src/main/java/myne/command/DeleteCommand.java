package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.TaskStorage;

import myne.InvalidCommandException;

import myne.task.Task;

public class DeleteCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    public DeleteCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
        try {
            // Delete task and save.
            int index = Integer.parseInt(parameters) - 1;
            Task removedTask = taskList.delete(index);
            storage.saveTasks(taskList);

            // Show message.
            ui.showMessage("Let me take that back.\n  " + removedTask);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(parameters + " is not a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
        }
    }
}
