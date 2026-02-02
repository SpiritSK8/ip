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
        StringBuilder sb = new StringBuilder("E");
        sb.append(TaskParser.SEPARATOR);
        sb.append(isDone() ? 1 : 0);
        sb.append(TaskParser.SEPARATOR);
        sb.append(getName());
        sb.append(TaskParser.SEPARATOR);
        sb.append(startDate);
        sb.append(TaskParser.SEPARATOR);
        sb.append(endDate);

        return sb.toString();
    }
}
