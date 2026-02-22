package emu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a description, start, and end date.
 */
public class Event extends Task {

    private static final String STORAGE_MARKER = "E";
    private static final String DISPLAY_MARKER = "[E]";
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    private String from;
    private String to;
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     * Attempts to convert {@code from} and {@code to} to a {@code LocalDate}.
     * If conversion fails, stores the original string.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);

        assert from != null && !from.isEmpty()
                : "from must not be null or empty";
        assert to != null && !to.isEmpty()
                : "to must not be null or empty";

        try {
            this.fromDate = LocalDate.parse(from);
            this.from = fromDate.format(DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            this.from = from;
        }

        try {
            this.toDate = LocalDate.parse(to);
            this.to = toDate.format(DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            this.to = to;
        }
    }

    /**
     * Returns the string representation of the task
     * for storage on the hard disk.
     *
     * @return The storage format of the event task.
     */
    @Override
    public String toStorageString() {
        return STORAGE_MARKER + super.toStorageString() + " | " + from + " | " + to;
    }

    /**
     * Returns the string representation of the task
     * when listed to the user.
     *
     * @return The display format of the event task.
     */
    @Override
    public String toString() {
        return DISPLAY_MARKER + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}