public class ExitCommand implements Command {
    private Myne myne;

    public ExitCommand(Myne myne) {
        this.myne = myne;
    }

    @Override
    public void execute() {
        myne.exit();
    }
}
