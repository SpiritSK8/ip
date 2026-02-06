package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.TaskStorage;

import myne.task.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class to encapsulate the logic for adding a <code>Deadline</code> and parsing the command for doing so.
 */
public class AddDeadlineCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-M[M]-d[d]][d[d]-M[M]-yyyy][d MMM yyyy]");

    /**
     * Creates a command that, when calling <code>execute()</code>, will add a <code>Deadline</code> task to the specified Myne instance.
     * @param myne Instance of Myne.
     * @param parameters Command parameters in the format of <code>&lt;name&gt; /by &lt;date in DD-MM-YYYY format&gt;</code>.
     *                   For example, "do homework /by 15-3-2026".
     */
    public AddDeadlineCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    /**
     * Adds the <code>Deadline</code> to Myne's task list and saves it to the file.
     * @throws InvalidCommandException If the parameters provided in the constructor do not match the format.
     */
    @Override
    public void execute() throws InvalidCommandException {
        // Add task and save.
        Deadline deadline = parseCommand(parameters);
        taskList.add(deadline);
        storage.saveTasks(taskList);

        // Show message.
        ui.showMessage("I entrust you with this task.\n  " + deadline.toString());
    }

    private Deadline parseCommand(String parameters) throws InvalidCommandException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException("myne.task.Deadline description cannot be empty.");
        }
        if (!parameters.contains("/by")) {
            throw new InvalidCommandException("Deadlines must have a /by.");
        }

        String[] parts = parameters.split("/by");

        if (parts.length < 2) {
            throw new InvalidCommandException("Missing description or due date.");
        }

        String name = parts[0].trim();
        String dueDateText = parts[1].trim();

        if (name.isEmpty() || dueDateText.isEmpty()) {
            throw new InvalidCommandException("Missing description or due date.");
        }

        LocalDate dueDate = LocalDate.parse(dueDateText, formatter);

        return new Deadline(name, dueDate);
    }
}
