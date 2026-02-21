package myne.command;

import myne.Myne;

import java.util.HashMap;

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
     * @param myne Instance of Myne that will be subjected to the command.
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

    /**
     * Extracts all the parameter names and corresponding values into a hashmap.<br>
     * <br>
     * Parameter names are signified by a forward slash (/) followed by a name without any space.
     * Everything after the space until just before the next forward slash is considered the parameter value.
     * Everything before the first forward slash is the first parameter value, and since it is
     * nameless, it will be assigned to the "first" key. Parameter names and values are trimmed.<br>
     * <br>
     * Unnamed parameters (forward slash followed immediately by a space) will be ignored.<br>
     * <br>
     * Example usage:
     * <pre>
     * {@code
     * String params = "summer festival /from 03-05-2026 /to     6 May 2026     /extra";
     * HashMap<String, String> paramValues = extractParameters(params);
     * paramValues.get("first"); // Returns "summer festival"
     * paramValues.get("/from"); // Returns "03-05-2026"
     * paramValues.get("/to"); // Returns "6 May 2026". Notice how the spaces are trimmed.
     * paramValues.get("/extra"); // Returns "";
     * }
     * </pre>
     * @param parameters The full parameter text, i.e. everything after the first word of the command.
     * @return A <code>HashMap&lt;String, String&gt;</code> that maps each parameter name to its corresponding value.
     */
    public static HashMap<String, String> extractParameters(String parameters) {
        HashMap<String, String> parameterValues = new HashMap<>();

        String[] parts = parameters.split("/");

        parameterValues.put("first", parts[0].trim()); // The first parameter doesn't have any /<parameter_name>

        for (int i = 1; i < parts.length; i++) {
            String parameterName = getFirstWord(parts[i]); // Doesn't need to be trimmed as getFirstWord() does that.
            String parameterValue = getAllAfterFirstWord(parts[i]).trim();

            if (parameterName.isBlank()) {
                continue;
            }

            parameterValues.put("/" + parameterName, parameterValue);
        }

        return parameterValues;
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