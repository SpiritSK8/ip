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
        String command = getFirstWord(input); // The command word.
        String parameters = getAllAfterFirstWord(input);
        return switch (command) {
            case "bye" -> new ExitCommand(myne);
            case "list" -> new ListCommand(myne);
            case "mark" -> new MarkCommand(myne, parameters);
            case "unmark" -> new UnmarkCommand(myne, parameters);
            case "todo" -> new AddTodoCommand(myne, parameters);
            case "deadline" -> new AddDeadlineCommand(myne, parameters);
            case "event" -> new AddEventCommand(myne, parameters);
            case "delete" -> new DeleteCommand(myne, parameters);
            case "find" -> new FindCommand(myne, parameters);
            case "help" -> new HelpCommand(parameters);
            default -> throw new InvalidCommandException("Come again?");
        };
    }

    private static String getFirstWord(String input) {
        String[] parts = input.split(" ", 2);
        return parts[0];
    }

    private static String getAllAfterFirstWord(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            return "";
        } else {
            return parts[1];
        }
    }
}