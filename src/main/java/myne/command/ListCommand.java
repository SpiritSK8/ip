package myne.command;

import myne.FerMyneFace;
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
    public Response execute() {
        if (taskList.isEmpty()) {
            return new Response("Hm... It appears you are under-worked. Shall we remedy that?",
                    Status.SUCCESS,
                    FerMyneFace.MYNE_CONFUSED);
        }

        String text = ui.getTaskListText(taskList, "Behold, your tasks!");
        return new Response(text, Status.SUCCESS, FerMyneFace.MYNE_HAPPY);
    }
}
