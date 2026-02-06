package myne.command;

import myne.Myne;

/**
 * Factory class for producing different commands.
 */
public class CommandParser {
    /**
     * Parses the first word of a command and creates the appropriate <code>Command</code> object based on it.<br>
     * <br>
     * The command parameters (the rest of the command), if present, are passed to the respective command classes.
     * The logic for parsing the command parameters are delegated to those classes.
     * @param input The full command text.
     * @param myne Instace of Myne that will be subjected to the command.
     * @return An instance of a class that implements the <code>Command</code> interface.
     * @throws InvalidCommandException If the first word of the command does not match any valid command.
     */
    public static Command parse(String input, Myne myne) throws InvalidCommandException {
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
