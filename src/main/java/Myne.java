import java.util.List;
import java.util.ArrayList;

public class Myne {
    private static final String DIVIDER = "________________________________________";
    
    private List<Task> list;
    private boolean isAlive;

    public Myne() {
        this.list = new ArrayList<>();
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
        list.add(task);

        System.out.println(DIVIDER);
        System.out.println("I entrust you with this task.");
        System.out.println("  " + task.toString());
        System.out.println(DIVIDER);
    }

    public void listItems() {
        if (list.isEmpty()) {
            System.out.println(DIVIDER);
            System.out.println("Hm... It appears you are under-worked. Shall we remedy that?");
            System.out.println(DIVIDER);
            return;
        }

        System.out.println(DIVIDER);
        System.out.println("Behold, your tasks!");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  " + (i + 1) + "." + list.get(i));
        }
        System.out.println(DIVIDER);
    }

    public void mark(int taskIndex) {
        list.get(taskIndex - 1).mark();

        System.out.println(DIVIDER);
        System.out.println("You have carried out your task with utmost diligence. Very good.");
        System.out.println("  " + list.get(taskIndex - 1).toString());
        System.out.println(DIVIDER);
    }

    public void unmark(int taskIndex) {
        list.get(taskIndex - 1).unmark();

        System.out.println(DIVIDER);
        System.out.println("Oh? You would like to redo it? Very well.");
        System.out.println("  " + list.get(taskIndex - 1).toString());
        System.out.println(DIVIDER);
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
