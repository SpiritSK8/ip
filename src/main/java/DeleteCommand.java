public class DeleteCommand implements Command {
    private MyneUi ui;
    private TaskList taskList;
    private TaskStorage storage;
    private String parameters;

    public DeleteCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
        try {
            // Delete task and save.
            int index = Integer.parseInt(parameters);
            Task removedTask = taskList.delete(index);
            storage.saveTasks(taskList);

            // Show message.
            ui.showDivider();
            System.out.println("Let me take that back.");
            System.out.println("  " + removedTask);
            ui.showDivider();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(parameters + " is not a valid task number.");
        }
    }
}
