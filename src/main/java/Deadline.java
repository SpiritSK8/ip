import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate date;

    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    private String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return getTypeIcon() + super.toString() + " (by: " + date.format(formatter) + ")";
    }

    @Override
    public String serialize() {
        return "D" + TaskParser.SEPARATOR +
                (isDone() ? 1 : 0) +
                TaskParser.SEPARATOR +
                getName() +
                TaskParser.SEPARATOR +
                date;
    }
}
