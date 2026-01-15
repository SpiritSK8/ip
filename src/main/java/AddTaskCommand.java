public class AddTaskCommand implements Command {
    private Myne myne;
    private String taskName;

    public AddTaskCommand(Myne myne, String taskName) {
        this.myne = myne;
        this.taskName = taskName;
    }

    @Override
    public void execute() {
        myne.addTask(taskName);
    }
}
