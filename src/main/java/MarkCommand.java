public class MarkCommand implements Command {
    private Myne myne;
    private String parameters;

    public MarkCommand(Myne myne, String parameters) {
        this.myne = myne;
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
        try {
            int index = Integer.parseInt(parameters);
            myne.mark(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(parameters + " is not a valid task number.");
        }
    }
}
