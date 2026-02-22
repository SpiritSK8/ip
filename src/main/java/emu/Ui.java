package emu;

/**
 * Handles formatting of messages shown to the user.
 */
public class Ui {
    private static final String DIVIDER_LINE =
            "___________________________\n";
    private static final String STANDARD_GREETING =
            "KONICHIWONDERHOY!! I'm Emu! What can I do for you today?";
    private static final String STORAGE_FAILURE =
            "UWA!!! I can't seem to find your past tasks!";

    /**
     * Returns the initial greeting message, with an additional message
     * if storage failed to initialise.
     *
     * @param hasStorageFailed Represents if storage has failed to initialise.
     * @return Greeting message.
     */
    public String giveGreeting(boolean hasStorageFailed) {
        if (hasStorageFailed) {
            return STANDARD_GREETING + " " + STORAGE_FAILURE;
        } else {
            return STANDARD_GREETING;
        }
    }

    /**
     * Formats a response message for display to the user.
     *
     * @param response Text to be formatted.
     * @return Formatted text.
     */
    public String formatResponse(String response) {
        assert response != null : "response must not be null";
        assert !response.isEmpty() : "response must not be empty";

        return DIVIDER_LINE + response + "\n" + DIVIDER_LINE;
    }
}