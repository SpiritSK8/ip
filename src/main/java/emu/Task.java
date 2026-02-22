package emu;

/**
 * Defines common variables and methods shared by all task types.
 * Each task has a description and a completion status.
 */
public abstract class Task {
    private String description;
    private boolean isComplete;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null && !description.isEmpty()
                : "description must not be null or empty";

        this.description = description;
        this.isComplete = false;
    }

    /**
     * Returns the status icon used for display.
     *
     * @return {@code "X"} if complete, otherwise a single space.
     */
    public String getStatusIcon() {
        return (isComplete ? "X" : " ");
    }

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as complete.
     */
    public void markComplete() {
        isComplete = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markIncomplete() {
        isComplete = false;
    }

    /**
     * Returns the string used for storage on the hard disk.
     * Subclasses may append extra information.
     *
     * @return String representation used for writing to disk.
     */
    public String toStorageString() {
        return " | " + getStatusIcon() + " | " + description;
    }

    /**
     * Returns the string representation of the task for display to the user.
     * Subclasses may append extra information.
     *
     * @return String representation used for listing.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}