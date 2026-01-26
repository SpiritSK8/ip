public class AddTodoCommand implements Command {
    private Myne myne;
    private String taskName;

    public AddTodoCommand(Myne myne, String taskName) {
        this.myne = myne;
        this.taskName = taskName.trim();
    }

    @Override
    public void execute() throws InvalidCommandException {
        if (taskName.isEmpty()) {
            throw new InvalidCommandException("Todo description cannot be empty.");
        }

        Todo todo = new Todo(taskName);
        myne.addTask(todo);
    }
}
