package myne.task;

import static myne.task.TaskParser.SEPARATOR;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

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

    @Override
    public String serialize() {
        return "E" + SEPARATOR +
                (isDone() ? 1 : 0) + SEPARATOR
                + getName() + SEPARATOR
                + startDate + SEPARATOR
                + endDate;
    }
}
