package myne.task;

/**
 * An abstract class that encapsulates the properties of a task.
 */
public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * Base constructor for tasks. All tasks have a name and status (is/is not done).
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "✔" : "";
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + name;
    }

    /**
     * Two tasks are equal if their types are the same and they have the same name and mark (is/is not done).
     * @param obj The other object to check equality with.
     * @return <code>true</code> if this task is equal with the other object, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Task other = (Task) obj;
        return (this.name.equals(other.name)) && (this.isDone == other.isDone);
    }

    /**
     * Returns a plaintext representation of this task that can be saved in the file.
     * @return The plaintext representation of this task.
     */
    public abstract String serialize();
}
