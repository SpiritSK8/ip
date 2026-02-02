public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    private String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString();
    }

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder("T");
        sb.append(TaskParser.SEPARATOR);
        sb.append(isDone() ? 1 : 0);
        sb.append(TaskParser.SEPARATOR);
        sb.append(getName());

        return sb.toString();
    }
}
