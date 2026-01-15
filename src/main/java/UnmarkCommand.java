public class UnmarkCommand implements Command {
    private Myne myne;
    private int taskIndex;

    public UnmarkCommand(Myne myne, int taskIndex) {
        this.myne = myne;
    }

    @Override
    public void execute() {
        myne.unmark(taskIndex);
    }
}
