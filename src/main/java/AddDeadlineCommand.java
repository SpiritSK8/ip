public class AddDeadlineCommand implements Command {
    private Myne myne;
    private String parameters;

    public AddDeadlineCommand(Myne myne, String parameters) {
        this.myne = myne;
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
        Deadline deadline = parseCommand(parameters);
        myne.addTask(deadline);
    }

    private Deadline parseCommand(String parameters) throws InvalidCommandException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException("Deadline description cannot be empty.");
        }
        if (!parameters.contains("/by")) {
            throw new InvalidCommandException("Deadlines must have a /by.");
        }

        String[] parts = parameters.split("/by");

        if (parts.length < 2) {
            throw new InvalidCommandException("Missing description or due date.");
        }

        String name = parts[0].trim();
        String dueDate = parts[1].trim();

        if (name.isEmpty() || dueDate.isEmpty()) {
            throw new InvalidCommandException("Missing description or due date.");
        }

        return new Deadline(name, dueDate);
    }
}
