package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.TaskStorage;

public class MarkCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    public MarkCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
        try {
            // Mark task and save.
            int index = Integer.parseInt(parameters) - 1;
            taskList.mark(index);
            storage.saveTasks(taskList);

            ui.showMessage("You have carried out your task with utmost diligence. Very good.\n  " + taskList.get(index).toString());
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(parameters + " is not a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
        }
    }
}
