package myne.task;

import static myne.task.TaskParser.SEPARATOR;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate dueDate;

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

    @Override
    public String serialize() {
        return "D" + SEPARATOR +
                (isDone() ? 1 : 0) + SEPARATOR +
                getName() + SEPARATOR +
                dueDate;
    }

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
