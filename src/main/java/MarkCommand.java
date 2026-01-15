public class MarkCommand implements Command {
    private Myne myne;
    private int taskIndex;

    public MarkCommand(Myne myne, int taskIndex) {
        this.myne = myne;
    }

    @Override
    public void execute() {
        myne.mark(taskIndex);
    }
}
