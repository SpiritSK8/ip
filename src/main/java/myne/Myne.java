package myne;

import myne.command.Command;
import myne.command.CommandParser;
import myne.task.TaskParser;

/**
 * <code>___..___.................</code><br>
 * <code>|..\/..|.................</code><br>
 * <code>| .... |_..._ _ __...___.</code><br>
 * <code>| |\/| | | | | '_ \ / _ \</code><br>
 * <code>| |..| | |_| | | | | .__/</code><br>
 * <code>\_|..|_/\__, |_| |_|\___|</code><br>
 * <code>.........__/ |...........</code><br>
 * <code>........|___/............</code>
 */
public class Myne {
    private final MyneUi ui;
    private final TaskStorage storage;
    private final TaskList taskList;
    private boolean isAlive;

    /**
     * Creates an instance of Myne that stores tasks in the specified <code>filePath</code>.
     * Myne stores tasks in a text (.txt) file.
     * Bring Myne to life by calling <code>run()</code>.
     * @param filePath The file path to the file storage.
     */
    public Myne(String filePath) {
        this.ui = new MyneUi();
        this.storage = new TaskStorage(filePath);
        this.taskList = new TaskList(TaskParser.parseTaskFile(storage.fetchTaskFile()));
        this.isAlive = true;
    }

    /**
     * Brings Myne to life. May this meeting be blessed.
     */
    public void run() {
        ui.showGreeting();

        while (isAlive()) {
            try {
                String input = ui.readInput();
                Command command = CommandParser.parse(input, this);
                command.execute();
            } catch (RuntimeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Stops Myne.
     */
    public void exit() {
        ui.showExit();
        this.isAlive = false;
    }

    public MyneUi getUi() {
        return this.ui;
    }

    public TaskStorage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public boolean isAlive() {
        return this.isAlive;
    }
}
