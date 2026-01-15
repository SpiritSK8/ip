import java.util.Locale;
import java.util.Scanner;

public class Myne {
    public static void main(String[] args) {
        greet();

        String exitMessage = "Farewell. May the time come when our threads of fate are woven together again.";
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            System.out.println("_____________________________________________");
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(exitMessage);
                System.out.println("_____________________________________________");
                break;
            } else {
                System.out.println(input);
                System.out.println("_____________________________________________");
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
}
