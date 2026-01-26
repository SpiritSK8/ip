import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Myne myne = new Myne();
        myne.greet();

        Scanner sc = new Scanner(System.in);
        while (myne.isAlive()) {
            String input = sc.nextLine();
            Command command = getCommand(input, myne);
            command.execute();
        }
    }

    private static Command getCommand(String input, Myne myne) {
        String[] parts = input.split(" ");
        String command = parts[0]; // The command word.
        String parameters = parts.length > 1 ? parts[1] : ""; // The rest of the input.
        return switch (command) {
            case "bye" -> new ExitCommand(myne);
            case "list" -> new ListCommand(myne);
            case "mark" -> new MarkCommand(myne, parameters);
            case "unmark" -> new UnmarkCommand(myne, parameters);
            case "todo" -> new AddTodoCommand(myne, parameters);
            default -> new AddTodoCommand(myne, input);
        };
    }
}
