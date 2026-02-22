package emu;

/**
 * Represents a to-do task that has only a description.
 */
public class ToDo extends Task {
    private static final String STORAGE_MARKER = "T";
    private static final String DISPLAY_MARKER = "[T]";

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
        assert description != null && !description.isEmpty() : "description must not be null or empty";
    }

    /**
     * Returns the string representation of the task for storage on disk.
     *
     * @return Storage format of the ToDo task.
     */
    @Override
    public String toStorageString() {
        return STORAGE_MARKER + super.toStorageString();
    }

    /**
     * Returns the string representation of the task for display to the user.
     *
     * @return Display format of the ToDo task.
     */
    @Override
    public String toString() {
        return DISPLAY_MARKER + super.toString();
    }
}