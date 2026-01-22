public class AddTodoCommand implements Command {
    private Myne myne;
    private Todo todo;

    public AddTodoCommand(Myne myne, String taskName) {
        this.myne = myne;
        this.todo = new Todo(taskName);
    }

    @Override
    public void execute() {
        myne.addTask(todo);
    }
}
