import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Myne {
    private static List<TodoItem> list;

    public static void main(String[] args) {
        list = new ArrayList<>();

        greet();

        String exitMessage = "Farewell. May the time come when our threads of fate are woven together again.";
        Scanner sc = new Scanner(System.in);
        boolean alive = true;
        while (alive) {
            String input = sc.nextLine();
            System.out.println("_____________________________________________");
            switch (input) {
                case "bye":
                    System.out.println(exitMessage);
                    System.out.println("_____________________________________________");
                    alive = false;
                    break;
                case "list":
                    listItems();
                    System.out.println("_____________________________________________");
                    break;
                default:
                    TodoItem todo = new TodoItem(input);
                    list.add(todo);
                    System.out.println("I have inscribed \"" + input + "\" into your list.");
                    System.out.println("_____________________________________________");
                    break;
            }
        }
    }

    private static void greet() {
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

    private static void listItems() {
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
