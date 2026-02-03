package myne;

import myne.command.Command;
import myne.command.CommandParser;
import myne.task.TaskParser;

public class Myne {
    private static final String DIVIDER = "________________________________________";

    private final MyneUi ui;
    private final TaskStorage storage;
    private final TaskList taskList;
    private boolean isAlive;

    public Myne(String filePath) {
        this.ui = new MyneUi();
        this.storage = new TaskStorage(filePath);
        this.taskList = new TaskList(TaskParser.parseTaskFile(storage.fetchTaskFile()));
        this.isAlive = true;
    }

    public void run() {
        ui.showGreeting();

        while (isAlive()) {
            try {
                String input = ui.readCommand();
                Command command = CommandParser.parse(input, this);
                command.execute();
            } catch (RuntimeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

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
