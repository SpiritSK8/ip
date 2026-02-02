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
        int isDone = this.isDone() ? 1 : 0;
        return String.format("T|%d|%s", isDone, this.getName());
    }
}
