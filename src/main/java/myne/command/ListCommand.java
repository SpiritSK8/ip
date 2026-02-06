package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;

public class ListCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;

    public ListCommand(Myne myne) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
    }

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
