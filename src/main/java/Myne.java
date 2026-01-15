import java.util.List;
import java.util.ArrayList;

public class Myne {
    private List<Task> list;

    public Myne() {
        this.list = new ArrayList<>();
    }

    public void greet() {
        String greetingStart = "Good day to you. My name is";
        String logo = """
                ___  ___
                |  \\/  |
                | .  . |_   _ _ __   ___
                | |\\/| | | | | '_ \\ / _ \\
                | |  | | |_| | | | |  __/
                \\_|  |_/\\__, |_| |_|\\___|
                         __/ |
                        |___/""";
        String greetingEnd = "May our meeting, ordained by the gods be blessed on this fruitful day.";

        System.out.println("_____________________________________________");
        System.out.println(greetingStart + "\n" + logo + "\n" + greetingEnd);
        System.out.println("_____________________________________________");
    }

    public void addTask(String name) {
        Task task = new Task(name);
        list.add(task);
    }

    public void listItems() {
        if (list.isEmpty()) {
            System.out.println("It appears there are no entries in your list.");
            return;
        }

        System.out.println("Certainly. Here is your list.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }
}
