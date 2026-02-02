import java.util.List;
import java.util.ArrayList;

public class Myne {
    private static final String DIVIDER = "________________________________________";
    
    private List<Task> taskList;
    private boolean isAlive;

    public Myne() {
        this.taskList = TaskParser.parseTaskFile(TaskFile.fetchTaskFile());
        this.isAlive = true;
    }

    public void greet() {
        final String greetingStart = "Good day to you. My name is";
        final String logo = """
                ___  ___
                |  \\/  |
                | .  . |_   _ _ __   ___
                | |\\/| | | | | '_ \\ / _ \\
                | |  | | |_| | | | |  __/
                \\_|  |_/\\__, |_| |_|\\___|
                         __/ |
                        |___/""";
        final String greetingEnd = "May our meeting, ordained by the gods be blessed on this fruitful day.";

        final String greetingMessage = greetingStart + "\n" + logo + "\n" + greetingEnd;

        System.out.println(DIVIDER);
        System.out.println(greetingMessage);
        System.out.println(DIVIDER);
    }

    public void addTask(Task task) {
        taskList.add(task);
        TaskFile.saveTasks(taskList);

        System.out.println(DIVIDER);
        System.out.println("I entrust you with this task.");
        System.out.println("  " + task.toString());
        System.out.println(DIVIDER);
    }

    public void listItems() {
        if (taskList.isEmpty()) {
            System.out.println(DIVIDER);
            System.out.println("Hm... It appears you are under-worked. Shall we remedy that?");
            System.out.println(DIVIDER);
            return;
        }

        System.out.println(DIVIDER);
        System.out.println("Behold, your tasks!");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("  " + (i + 1) + "." + taskList.get(i));
        }
        System.out.println(DIVIDER);
    }

    public void mark(int taskIndex) {
        try {
            taskList.get(taskIndex - 1).mark();

            System.out.println(DIVIDER);
            System.out.println("You have carried out your task with utmost diligence. Very good.");
            System.out.println("  " + taskList.get(taskIndex - 1).toString());
            System.out.println(DIVIDER);

        } catch (IndexOutOfBoundsException e) {
            System.out.println(DIVIDER);
            System.out.println("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
            System.out.println(DIVIDER);
        }

        TaskFile.saveTasks(taskList);
    }

    public void unmark(int taskIndex) {
        try {
            taskList.get(taskIndex - 1).unmark();

            System.out.println(DIVIDER);
            System.out.println("Ah, you would like to redo it? Very well.");
            System.out.println("  " + taskList.get(taskIndex - 1).toString());
            System.out.println(DIVIDER);

        } catch (IndexOutOfBoundsException e) {
            System.out.println(DIVIDER);
            System.out.println("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
            System.out.println(DIVIDER);
        }

        TaskFile.saveTasks(taskList);
    }

    public void delete(int taskIndex) {
        try {
            Task removedTask = taskList.remove(taskIndex - 1);

            System.out.println(DIVIDER);
            System.out.println("Let me take that back.");
            System.out.println("  " + removedTask);
            System.out.println(DIVIDER);

        } catch (IndexOutOfBoundsException e) {
            System.out.println(DIVIDER);
            System.out.println("Oh my! It seems that you only have " + taskList.size() + " tasks at present.");
            System.out.println(DIVIDER);
        }

        TaskFile.saveTasks(taskList);
    }

    public void exit() {
        System.out.println(DIVIDER);
        System.out.println("Farewell. May the time come when our threads of fate are woven together again.");
        System.out.println(DIVIDER);
        this.isAlive = false;
    }

    public boolean isAlive() {
        return this.isAlive;
    }
}
