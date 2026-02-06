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

    /**
     * Returns a plaintext representation of this task that can be saved in the file.
     * @return The plaintext representation of this task.
     */
    @Override
    public String serialize() {
        return "T" + SEPARATOR
                + (isDone() ? 1 : 0) + SEPARATOR
                + getName();
    }

    /**
     * Returns <code>true</code> if and only if the other object is a <code>Todo</code>
     * with the same name and mark as this deadline's.
     * @param obj The other object to check equality with.
     * @return <code>true</code> if this deadline is equal with the other object, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo other)) {
            return false;
        }

        // Check if names are equal.
        return super.equals(other);
    }
}
