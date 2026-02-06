package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.TaskStorage;
import myne.task.Deadline;
import myne.task.Todo;

/**
 * A class that encapsulates the logic for finding tasks and parsing the command for it.
 */
public class FindCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final String parameters;

    public FindCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException("A search cannot be performed without any text."); // Cannot find empty string.
        }

        TaskList tasksFound = taskList.find(parameters);

        if (tasksFound.isEmpty()) {
            ui.showMessage("I could not find anything of the sort.");
            return;
        }

        ui.showTaskList(tasksFound, "Here are what I found from the archive.");
    }
}
