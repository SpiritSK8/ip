public class MarkCommand implements Command {
    private Myne myne;
    private int taskIndex;

    public MarkCommand(Myne myne, String taskIndex) {
        this.myne = myne;
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public void execute() {
        myne.mark(taskIndex);
    }
}
