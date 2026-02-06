package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.TaskStorage;

import myne.task.Todo;

/**
 * A class to encapsulate the logic for adding a <code>Todo</code> and parsing the command for doing so.
 */
public class AddTodoCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String taskName;

    /**
     * Creates a command that, when calling <code>execute()</code>, will add a <code>Todo</code> task to the specified Myne instance.
     * @param myne Instance of Myne.
     * @param taskName The description of the task.
     */
    public AddTodoCommand(Myne myne, String taskName) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.taskName = taskName.trim();
    }

    /**
     * Adds the <code>Todo</code> to Myne's task list and saves it to the file.
     * @throws InvalidCommandException If <code>this.taskName</code> is empty.
     */
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
        ui.showMessage("I entrust you with this task.\n  " + todo.toString());
    }
}
