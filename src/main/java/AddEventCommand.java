public class AddEventCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    public AddEventCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
        // Add task and save.
        Event event = parseCommand(parameters);
        taskList.add(event);
        storage.saveTasks(taskList);

        // Show message.
        ui.showMessage("I entrust you with this task.\n  " + event.toString());
    }

    private Event parseCommand(String parameters) throws InvalidCommandException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException("Event description cannot be empty.");
        }
        if (!parameters.contains("/from") || !parameters.contains("/to")) {
            throw new InvalidCommandException("Events must have a /from and /to.");
        }

        String[] parts = parameters.split("/from");

        if (parts.length < 2) {
            throw new InvalidCommandException("Missing description, start time, or end time.");
        }

        String name = parts[0].trim();

        parts = parts[1].split("/to");

        if (parts.length < 2) {
            throw new InvalidCommandException("Missing description, start time, or end time.");
        }

        String from = parts[0].trim();
        String to = parts[1].trim();

        if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidCommandException("Missing description, start time, or end time.");
        }

        return new Event(name, from, to);
    }
}
