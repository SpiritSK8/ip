import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Myne myne = new Myne();
        myne.greet();

        Scanner sc = new Scanner(System.in);
        boolean alive = true;
        while (alive) {
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
            default -> new AddTaskCommand(myne, input);
        };
    }
}
