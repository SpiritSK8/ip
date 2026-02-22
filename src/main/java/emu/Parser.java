package emu;

/**
 * Parses user input to determine the command type
 * and its associated parameters.
 */
public class Parser {

    private final String command; // Command portion that dictates which method to invoke.
    private final String other; // Remaining portion passed as argument to method.

    /**
     * Constructs a Parser from the full user response, splitting it
     * into command and other components.
     *
     * @param fullResponse Full input string from the user.
     */
    public Parser(String fullResponse) {
        assert fullResponse != null : "fullResponse must not be null";

        String trimmedResponse = fullResponse.trim();
        int firstSpace = trimmedResponse.indexOf(' ');

        this.command = (firstSpace == -1)
                ? trimmedResponse
                : trimmedResponse.substring(0, firstSpace);

        this.other = (firstSpace == -1)
                ? ""
                : trimmedResponse.substring(firstSpace + 1);
    }

    /**
     * Returns the command component parsed from the input.
     *
     * @return The command string.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the argument component parsed from the input.
     *
     * @return The other string (arguments), or an empty string if none.
     */
    public String getOther() {
        return other;
    }

    /**
     * Validates the userInput for creating a ToDo task.
     *
     * @param userInput User input for a ToDo task.
     * @throws EmuException If the userInput is empty.
     */
    public static void verifyTodo(String userInput) throws EmuException {
        assert userInput != null : "userInput must not be null";

        if (userInput.trim().isEmpty()) {
            throw new EmuException("You can't make a todo without a description!");
        }
    }

    /**
     * Parses the userInput for creating a Deadline task.
     *
     * @param userInput User input for a Deadline task.
     * @return A String array containing description and date.
     * @throws EmuException If userInput is missing /by or description/date is empty.
     */
    public static String[] parseDeadline(String userInput) throws EmuException {
        assert userInput != null : "userInput must not be null";

        int slash = userInput.indexOf("/by");
        if (slash == -1) {
            throw new EmuException("You forgot to include /by in your deadline task!");
        }

        String desc = userInput.substring(0, slash).trim();
        String by = userInput.substring(slash + 3).trim();

        if (desc.isEmpty() || by.isEmpty()) {
            throw new EmuException("You can't make a deadline without a description and a by date silly!!");
        }

        return new String[] { desc, by };
    }

    /**
     * Parses the userInput for creating an Event task.
     *
     * @param userInput User input for an Event task.
     * @return A String array containing description, from, and to dates.
     * @throws EmuException If format is incorrect or any field is empty.
     */
    public static String[] parseEvent(String userInput) throws EmuException {
        assert userInput != null : "userInput must not be null";

        int slashFrom = userInput.indexOf("/from");
        int slashTo = userInput.indexOf("/to");

        if (slashFrom == -1 || slashTo == -1 || slashTo < slashFrom) {
            throw new EmuException("Incorrect format! Use: event (desc) /from (start) /to (end)");
        }

        String desc = userInput.substring(0, slashFrom).trim();
        String from = userInput.substring(slashFrom + 5, slashTo).trim();
        String to = userInput.substring(slashTo + 3).trim();

        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new EmuException("You can't make an event without a description, a from date, and a to date silly!!");
        }

        return new String[] { desc, from, to };
    }

    /**
     * Parses the given string as an integer.
     *
     * @param stringNumber String to convert to int.
     * @return Parsed integer value.
     * @throws EmuException If the string is not a valid integer.
     */
    public static int parseNumber(String stringNumber) throws EmuException {
        assert stringNumber != null : "stringNumber must not be null";

        try {
            return Integer.parseInt(stringNumber.trim());
        } catch (NumberFormatException e) {
            throw new EmuException("That's not a valid number!");
        }
    }
}