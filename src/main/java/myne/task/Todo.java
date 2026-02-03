package myne.task;

import static myne.task.TaskParser.SEPARATOR;

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
        return "T" + SEPARATOR +
                (isDone() ? 1 : 0) + SEPARATOR +
                getName();
    }
}
