package myne.task;

import static myne.task.TaskParser.SEPARATOR;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A <code>Task</code> type with a single due date.
 */
public class Deadline extends Task {
    private final LocalDate dueDate;

    /**
     * Creates a task with a single due date.
     * @param name The name of the task.
     * @param dueDate The due date of the task.
     */
    public Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    private String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return getTypeIcon() + super.toString() + " (by: " + dueDate.format(formatter) + ")";
    }

    /**
     * Returns a plaintext representation of this task that can be saved in the file.
     * @return The plaintext representation of this task.
     */
    @Override
    public String serialize() {
        return "D" + SEPARATOR +
                (isDone() ? 1 : 0) + SEPARATOR +
                getName() + SEPARATOR +
                dueDate;
    }

    /**
     * Returns <code>true</code> if and only if the other object is a <code>Deadline</code>
     * and its name, mark, and due date are equal with this deadline's.
     * @param obj The other object to check equality with.
     * @return <code>true</code> if this deadline is equal with the other object, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline other)) {
            return false;
        }

        // Check if names are equal.
        if (!super.equals(obj)) {
            return false;
        }

        return this.dueDate.equals(other.dueDate);
    }
}
