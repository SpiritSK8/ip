import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String DIVIDER = "________________________________________";

        Myne myne = new Myne();
        myne.greet();

        Scanner sc = new Scanner(System.in);
        while (myne.isAlive()) {
            String input = sc.nextLine();
            try {
                Command command = getCommand(input, myne);
                command.execute();
            } catch (RuntimeException e) {
                System.out.println(DIVIDER);
                System.out.println(e.getMessage());
                System.out.println(DIVIDER);
            }
        }
    }

    private static Command getCommand(String input, Myne myne) throws InvalidCommandException {
        String[] parts = input.split(" ");
        String command = parts[0]; // The command word.
        String parameters = parts.length > 1 ? input.substring(parts[0].length() + 1) : ""; // The rest of the input.
        return switch (command) {
            case "bye" -> new ExitCommand(myne);
            case "list" -> new ListCommand(myne);
            case "mark" -> new MarkCommand(myne, parameters);
            case "unmark" -> new UnmarkCommand(myne, parameters);
            case "todo" -> new AddTodoCommand(myne, parameters);
            case "deadline" -> new AddDeadlineCommand(myne, parameters);
            case "event" -> new AddEventCommand(myne, parameters);
            case "delete" -> new DeleteCommand(myne, parameters);
            default -> throw new InvalidCommandException("Come again?");
        };
    }
}
