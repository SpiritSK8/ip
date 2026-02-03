import java.util.List;
import java.util.ArrayList;

public class Myne {
    private static final String DIVIDER = "________________________________________";

    private MyneUi ui;
    private List<Task> taskList;
    private boolean isAlive;

    public Myne() {
        this.ui = new MyneUi();
        this.taskList = TaskParser.parseTaskFile(TaskFile.fetchTaskFile());
        this.isAlive = true;
    }

    public void greet() {
        ui.showGreeting();
    }

    public void addTask(Task task) {
        taskList.add(task);
        TaskFile.saveTasks(taskList);

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
            TaskFile.saveTasks(taskList);

            ui.showMark(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
        }
    }

    public void unmark(int taskIndex) {
        try {
            Task task = taskList.get(taskIndex - 1);
            task.unmark();
            TaskFile.saveTasks(taskList);

            ui.showUnmark(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
        }
    }

    public void delete(int taskIndex) {
        try {
            Task removedTask = taskList.remove(taskIndex - 1);
            TaskFile.saveTasks(taskList);

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
