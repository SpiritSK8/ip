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

        ui.showTaskList(taskList, "Behold, your tasks!");
    }
}
