public class ListCommand implements Command {
    private Myne myne;

    public ListCommand(Myne myne) {
        this.myne = myne;
    }

    @Override
    public void execute() {
        myne.listItems();
    }
}
