public class MarkCommand implements Command {
    private MyneUi ui;
    private TaskList taskList;
    private TaskStorage storage;
    private String parameters;

    public MarkCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    @Override
    public void execute() throws InvalidCommandException {
        try {
            // Mark task and save.
            int index = Integer.parseInt(parameters);
            taskList.mark(index);
            storage.saveTasks(taskList);

            // Show message.
            ui.showDivider();
            System.out.println("You have carried out your task with utmost diligence. Very good.");
            System.out.println("  " + taskList.get(index).toString());
            ui.showDivider();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(parameters + " is not a valid task number.");
        }
    }
}
