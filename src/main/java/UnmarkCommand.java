public class UnmarkCommand implements Command {
    private Myne myne;
    private int taskIndex;

    public UnmarkCommand(Myne myne, String taskIndex) {
        this.myne = myne;
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public void execute() {
        myne.unmark(taskIndex);
    }
}
