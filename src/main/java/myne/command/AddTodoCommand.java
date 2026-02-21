package myne.command;

import myne.FerMyneFace;
import myne.Myne;
import myne.TaskList;
import myne.TaskStorage;
import myne.task.Todo;

/**
 * A class to encapsulate the logic for adding a <code>Todo</code> and parsing the command for doing so.
 */
public class AddTodoCommand implements Command {
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String taskName;

    /**
     * Creates a command that, when calling <code>execute()</code>,
     * will add a <code>Todo</code> task to the specified Myne instance.
     * @param myne Instance of Myne.
     * @param taskName The description of the task.
     */
    public AddTodoCommand(Myne myne, String taskName) {
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.taskName = taskName.trim();
    }

    /**
     * Adds the <code>Todo</code> to Myne's task list and saves it to the file.
     * @throws InvalidCommandException If <code>this.taskName</code> is empty.
     */
    @Override
    public Response execute() throws InvalidCommandException {
        if (taskName.isEmpty()) {
            throw new InvalidCommandException("The task name cannot be nothing, can it?", FerMyneFace.MYNE_WORRIED);
        }

        // Add task and save.
        Todo todo = new Todo(taskName);
        taskList.add(todo);
        storage.saveTasks(taskList);

        return new Response("I entrust you with this task.\n\n" + todo.toString(),
                Status.SUCCESS,
                FerMyneFace.MYNE_THANKFUL);
    }
}
