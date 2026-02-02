public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    private String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (by: " + date + ")";
    }

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder("D");
        sb.append(TaskParser.SEPARATOR);
        sb.append(isDone() ? 1 : 0);
        sb.append(TaskParser.SEPARATOR);
        sb.append(getName());
        sb.append(TaskParser.SEPARATOR);
        sb.append(date);

        return sb.toString();
    }
}
