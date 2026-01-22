public class Deadline extends Task{
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
        return getTypeIcon() + super.toString() + "(by: " + date + ")";
    }
}
