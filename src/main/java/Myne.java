import java.util.List;
import java.util.Scanner;

public class Myne {
    private static final String DIVIDER = "________________________________________";

    private String filePath;

    private MyneUi ui;
    private TaskStorage storage;
    private TaskList taskList;
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
            String input = ui.readCommand();
            try {
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
