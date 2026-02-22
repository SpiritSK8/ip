package emu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a description and a by date.
 */
public class Deadline extends Task {
    private static final String STORAGE_MARKER = "D";
    private static final String DISPLAY_MARKER = "[D]";
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    private String by;
    private LocalDate byDate;

    /**
     * Creates a deadline task with the specified description and by date.
     * Attempts to convert {@code by} to a {@code LocalDate}.
     * If conversion fails, stores the original string.
     *
     * @param description Description of the deadline task.
     * @param by By date as a string.
     */
    public Deadline(String description, String by) {
        super(description);

        assert by != null : "by String should not be null";

        try {
            this.byDate = LocalDate.parse(by);
            this.by = byDate.format(DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    /**
     * Returns the string representation of the task for storage on hard disk.
     *
     * @return Storage format of a deadline task.
     */
    @Override
    public String toStorageString() {
        return STORAGE_MARKER + super.toStorageString() + " | " + by;
    }

    /**
     * Returns the string representation of the task when listed to the user.
     *
     * @return Display format of a deadline task.
     */
    @Override
    public String toString() {
        return DISPLAY_MARKER + super.toString() + " (by: " + by + ")";
    }
}