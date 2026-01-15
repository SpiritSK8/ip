import java.util.List;
import java.util.ArrayList;

public class Myne {
    private List<Task> list;

    public Myne() {
        this.list = new ArrayList<>();
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

        System.out.println("_____________________________________________");
        System.out.println(greetingMessage);
        System.out.println("_____________________________________________");
    }

    public void addTask(String name) {
        Task task = new Task(name);
        list.add(task);
        System.out.println("I have inscribed \"" + name + "\" into your list.");
        System.out.println("_____________________________________________");
    }

    public void listItems() {
        if (list.isEmpty()) {
            System.out.println("It appears there are no entries in your list.");
            System.out.println("_____________________________________________");
            return;
        }

        System.out.println("Certainly. Here is your list.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println("_____________________________________________");
    }

    public void mark(int taskIndex) {
        list.get(taskIndex).mark();
        System.out.println("Very good. You have done your job well.");
        System.out.println("    " + list.get(taskIndex).toString());
        System.out.println("_____________________________________________");
    }

    public void unmark(int taskIndex) {
        list.get(taskIndex).unmark();
        System.out.println("Oh? You would like to redo your task? Very well.");
        System.out.println("    " + list.get(taskIndex).toString());
        System.out.println("_____________________________________________");
    }

    public void exit() {
        System.out.println("Farewell. May the time come when our threads of fate are woven together again.");
        System.out.println("_____________________________________________");
    }
}
