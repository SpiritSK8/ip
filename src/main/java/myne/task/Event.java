package myne.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A <code>Task</code> type with a start and end date.
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates a task with a start and end date.
     * @param name The name of the task.
     * @param startDate The start date of the task.
     * @param endDate The end date of the task.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return getTypeIcon()
                + super.toString()
                + " (from: " + startDate.format(formatter)
                + " to: " + endDate.format(formatter) + ")";
    }

    /**
     * Returns a plaintext representation of this task that can be saved in the file.
     * @return The plaintext representation of this task.
     */
    @Override
    public String serialize() {
        return "E" + TaskParser.separator()
                + (isDone() ? 1 : 0) + TaskParser.separator()
                + getName() + TaskParser.separator()
                + startDate + TaskParser.separator()
                + endDate;
    }

    /**
     * Returns <code>true</code> if and only if the other object is an <code>Event</code>
     * and its name, mark, start date, and end date are equal with this event's.
     * @param obj The other object to check equality with.
     * @return <code>true</code> if this event is equal with the other object, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event other)) {
            return false;
        }

        // Check if names are equal.
        if (!super.equals(obj)) {
            return false;
        }

        return this.startDate.equals(other.startDate) && this.endDate.equals(other.endDate);
    }
}
