public class AddTodoCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String taskName;

    public AddTodoCommand(Myne myne, String taskName) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.taskName = taskName.trim();
    }

    @Override
    public void execute() throws InvalidCommandException {
        if (taskName.isEmpty()) {
            throw new InvalidCommandException("Todo description cannot be empty.");
        }

        // Add task and save.
        Todo todo = new Todo(taskName);
        taskList.add(todo);
        storage.saveTasks(taskList);

        // Show message.
        ui.showDivider();
        System.out.println("I entrust you with this task.");
        System.out.println("  " + todo.toString());
        ui.showDivider();
    }
}
