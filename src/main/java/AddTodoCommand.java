public class AddTodoCommand implements Command {
    private Myne myne;
    private String taskName;

    public AddTodoCommand(Myne myne, String taskName) {
        this.myne = myne;
        this.taskName = taskName;
    }

    @Override
    public void execute() {
        myne.addTask(taskName);
    }
}
