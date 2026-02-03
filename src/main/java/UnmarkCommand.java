public class UnmarkCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    public UnmarkCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
        try {
            // Unmark task and save.
            int index = Integer.parseInt(parameters);
            taskList.unmark(index);
            storage.saveTasks(taskList);

            // Show message.
            ui.showDivider();
            System.out.println("Ah, you would like to redo it? Very well.");
            System.out.println("  " + taskList.get(index).toString());
            ui.showDivider();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(parameters + " is not a valid task number.");
        }
    }
}
