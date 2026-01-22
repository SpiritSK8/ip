public class Todo extends Task{
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
}
