package emu;

/**
 * Manages the format of the error messages in Emu.
 */
public class EmuException extends Exception {
    /**
     * Creates an EmuException with the specified detail message.
     *
     * @param message The custom message describing the exception.
     */
    public EmuException(String message) {
        super(message);

        assert message != null : "Message should not be null";
        assert !message.isEmpty() : "Message should not be empty";
    }

    /**
     * Returns the formatted exception message prefixed with a custom string.
     *
     * @return Formatted exception message.
     */
    @Override
    public String getMessage() {
        return "UWA!!! " + super.getMessage();
    }

    /**
     * Returns the actual message stored in EmuException for testing.
     *
     * @return Actual message stored in EmuException.
     */
    public String getRawMessage() {
        return super.getMessage();
    }
}