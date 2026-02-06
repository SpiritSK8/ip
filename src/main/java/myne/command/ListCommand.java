package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;

/**
 * A class to encapsulate the logic for listing the tasks in Myne.
 */
public class ListCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;

    /**
     * Creates a command that, when calling <code>execute()</code>, will list all tasks from the Myne instance.
     * @param myne Instance of Myne.
     */
    public ListCommand(Myne myne) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
    }

    /**
     * Lists all the tasks from the Myne instance.
     */
    @Override
    public void execute() {
        if (taskList.isEmpty()) {
            ui.showMessage("Hm... It appears you are under-worked. Shall we remedy that?");
            return;
        }

        ui.showTaskList(taskList, "Behold, your tasks!");
    }
}
