package emu;

/**
 * Manages the format of the error messages in Emu
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
     * Overrides extending class Exception for a custom message
     *
     * @return Custom String to be printed for EmuException message
     */
    @Override
    public String getMessage() {
        return "UWA!!! " + super.getMessage();
    }

    /**
     * Returns the actual message stored in EmuException for testing
     *
     * @return Actual message stored in EmuException
     */
    public String realMessage() {
        return super.getMessage();
    }
}