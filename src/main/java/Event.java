public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }

    @Override
    public String serialize() {
        int isDone = this.isDone() ? 1 : 0;
        return String.format("T|%d|%s|%s|%s", isDone, this.getName(), this.startDate, this.endDate);
    }
}
