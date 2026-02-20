package emu;

/**
 * Parses user input to determine the command type
 * and its associated parameters
 */
public class Parser {
    private final String command; // Command portion that dictates which method to invoke
    private final String other; // Remaining portion passed as argument to method

    /**
     * Creates a Parser from the full user response, splitting
     * it into command and other components
     *
     * @param fullResponse Full input string from the user
     */
    public Parser(String fullResponse) {
        assert fullResponse != null : "fullResponse should not be null";

        int firstSpace = fullResponse.indexOf(' ');
        this.command = (firstSpace == -1)
                ? fullResponse
                : fullResponse.substring(0, firstSpace);

        this.other = (firstSpace == -1)
                ? ""
                : fullResponse.substring(firstSpace + 1);
    }

    public String getCommand() {
        return command;
    }


    public String getOther() {
        return other;
    }

    /**
     * Validates the response for creating a ToDo task
     *
     * @param response User input for a ToDo task
     * @throws EmuException If the response is empty
     */
    public static void verifyTodo(String response) throws EmuException {
        assert response != null : "response should not be null";

        if (response.isEmpty()) {
            throw new EmuException("You can't make a todo without a description!");
        }
    }

    /**
     * Validates and parses the response for creating a Deadline task
     *
     * @param response User input for a Deadline task
     * @return A String array containing description and date
     * @throws EmuException If response is missing /by or description/date is empty
     */
    public static String[] parseDeadline(String response) throws EmuException {
        assert response != null : "response should not be null";

        int slash = response.indexOf("/by");
        if (slash == -1) {
            throw new EmuException("You forgot to include /by in your deadline task!");
        }

        String desc = response.substring(0, slash).trim();
        String by = response.substring(slash + 3).trim();

        if (desc.isEmpty() || by.isEmpty()) {
            throw new EmuException("You can't make a deadline without a description and a by date silly!!");
        }

        return new String[] { desc, by };
    }

    /**
     * Validates and parses the response for creating an Event task
     *
     * @param response User input for an Event task
     * @return A String array containing description, from, and to dates
     * @throws EmuException If format is incorrect or any field is empty
     */
    public static String[] parseEvent(String response) throws EmuException {
        assert response != null : "response should not be null";

        int slashFrom = response.indexOf("/from");
        int slashTo = response.indexOf("/to");

        if (slashFrom == -1 || slashTo == -1 || slashTo < slashFrom) {
            throw new EmuException("Incorrect format! Use: event (desc) /from (start) /to (end)");
        }

        String desc = response.substring(0, slashFrom).trim();
        String from = response.substring(slashFrom + 5, slashTo).trim();
        String to = response.substring(slashTo + 3).trim();

        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new EmuException("You can't make an event without a description, a from date, and a to date silly!!");
        }

        return new String[] { desc, from, to };
    }

    /**
     * Validates that the given string represents a valid integer
     *
     * @param stringNumber String to convert to int
     * @return Parsed integer value
     * @throws EmuException If string is not a valid integer
     */
    public static int parseNumber(String stringNumber) throws EmuException {
        assert stringNumber != null : "stringNumber should not be null";

        try {
            return Integer.parseInt(stringNumber);
        } catch (NumberFormatException e) {
            throw new EmuException("That's not a valid number!");
        }
    }
}