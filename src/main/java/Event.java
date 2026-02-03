import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String name, String startDate, String endDate) throws DateTimeParseException {
        super(name);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-M[M]-d[d]][d MMM yyyy]");
        this.startDate = LocalDate.parse(startDate, formatter);
        this.endDate = LocalDate.parse(endDate, formatter);
    }

    private String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return getTypeIcon() + super.toString() + " (from: " + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")";
    }

    @Override
    public String serialize() {
        return "E" + TaskParser.SEPARATOR +
                (isDone() ? 1 : 0) +
                TaskParser.SEPARATOR +
                getName() +
                TaskParser.SEPARATOR +
                startDate +
                TaskParser.SEPARATOR +
                endDate;
    }
}
