import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Myne myne = new Myne();
        myne.greet();

        Scanner sc = new Scanner(System.in);
        while (myne.isAlive()) {
            String input = sc.nextLine();
            Command command = parseInput(input, myne);
            command.execute();
        }
    }

    private static Command parseInput(String input, Myne myne) {
        String[] parts = input.split(" ");
        return switch (parts[0]) {
            case "bye" -> new ExitCommand(myne);
            case "list" -> new ListCommand(myne);
            case "mark" -> new MarkCommand(myne, Integer.parseInt(parts[1]));
            case "unmark" -> new UnmarkCommand(myne, Integer.parseInt(parts[1]));
            case "todo" -> new AddTodoCommand(myne, input.substring(parts[0].length() + 1)); // discards the first word to extract the rest of the command (the to do).
            default -> new AddTodoCommand(myne, input);
        };
    }
}
