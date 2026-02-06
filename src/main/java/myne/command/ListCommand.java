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

        StringBuilder sb = new StringBuilder("Behold, your tasks!\n");
        for (int i = 0; i < taskList.size() - 1; i++) {
            sb.append("  ").append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        sb.append("  ")
                .append(taskList.size())
                .append(".")
                .append(taskList.get(taskList.size() - 1)); // Last line doesn't need line break.

        ui.showMessage(sb.toString());
    }
}
