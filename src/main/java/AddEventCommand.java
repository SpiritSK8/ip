public class AddEventCommand implements Command {
    private Myne myne;
    private Event event;

    public AddEventCommand(Myne myne, String parameters) {
        this.myne = myne;
        this.event = parseCommand(parameters);
    }

    @Override
    public void execute() {
        myne.addTask(event);
    }

    private Event parseCommand(String parameters) {
        String[] parts = parameters.split("/from");
        String name = parts[0].trim();

        parts = parts[1].split("/to");
        String from = parts[0].trim();
        String to = parts[1].trim();

        return new Event(name, from, to);
    }
}
