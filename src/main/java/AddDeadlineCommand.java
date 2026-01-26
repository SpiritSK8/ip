public class AddDeadlineCommand implements Command {
    private Myne myne;
    private Deadline deadline;

    public AddDeadlineCommand(Myne myne, String parameters) {
        this.myne = myne;
        this.deadline = parseCommand(parameters);
    }

    @Override
    public void execute() {
        myne.addTask(deadline);
    }

    private Deadline parseCommand(String parameters) {
        String[] parts = parameters.split("/by");
        return new Deadline(parts[0].trim(), parts[1].trim());
    }
}
