import java.util.List;

public class Myne {
    private static final String DIVIDER = "________________________________________";

    private MyneUi ui;
    private TaskStorage storage;
    private List<Task> taskList;
    private boolean isAlive;

    public Myne() {
        this.ui = new MyneUi();
        this.storage = new TaskStorage("./data/myne.txt");
        this.taskList = TaskParser.parseTaskFile(storage.fetchTaskFile());
        this.isAlive = true;
    }

    public void greet() {
        ui.showGreeting();
    }

    public void addTask(Task task) {
        taskList.add(task);
        storage.saveTasks(taskList);

        ui.showAddTask(task);
    }

    public void listItems() {
        if (taskList.isEmpty()) {
            ui.showEmptyList();
        } else {
            ui.showList(taskList);
        }
    }

    public void mark(int taskIndex) {
        try {
            Task task = taskList.get(taskIndex - 1);
            task.mark();
            storage.saveTasks(taskList);

            ui.showMark(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
        }
    }

    public void unmark(int taskIndex) {
        try {
            Task task = taskList.get(taskIndex - 1);
            task.unmark();
            storage.saveTasks(taskList);

            ui.showUnmark(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
        }
    }

    public void delete(int taskIndex) {
        try {
            Task removedTask = taskList.remove(taskIndex - 1);
            storage.saveTasks(taskList);

            ui.showDelete(removedTask);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
        }
    }

    public void exit() {
        ui.showExit();
        this.isAlive = false;
    }

    public boolean isAlive() {
        return this.isAlive;
    }
}
