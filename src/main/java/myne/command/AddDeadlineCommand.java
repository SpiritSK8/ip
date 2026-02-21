package myne.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import myne.Myne;
import myne.TaskList;
import myne.TaskStorage;
import myne.task.Deadline;

/**
 * A class to encapsulate the logic for adding a <code>Deadline</code> and parsing the command for doing so.
 */
public class AddDeadlineCommand implements Command {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "[yyyy-M[M]-d[d]][d[d]-M[M]-yyyy][d MMM yyyy]");

    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    /**
     * Creates a command that, when calling <code>execute()</code>,
     * will add a <code>Deadline</code> task to the specified Myne instance.
     * @param myne Instance of Myne.
     * @param parameters Command parameters in the format of <code>&lt;name&gt;
     *                   /by &lt;date in DD-MM-YYYY format&gt;</code>.
     *                   For example, "do homework /by 15-3-2026".
     */
    public AddDeadlineCommand(Myne myne, String parameters) {
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    /**
     * Adds the <code>Deadline</code> to Myne's task list and saves it to the file.
     * @throws InvalidCommandException If the parameters provided in the constructor do not match the format.
     */
    @Override
    public Response execute() throws InvalidCommandException {
        // Add task and save.
        Deadline deadline = parseCommand(parameters);
        taskList.add(deadline);
        storage.saveTasks(taskList);

        return new Response("I entrust you with this task.\n" + deadline.toString(), Status.SUCCESS);
    }

    private Deadline parseCommand(String parameters) throws InvalidCommandException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException("Please provide the task details.");
        }

        HashMap<String, String> parameterValues = CommandParser.extractParameters(parameters);

        if (!parameterValues.containsKey("/by")) {
            throw new InvalidCommandException("Please provide the due date with /by.");
        }

        if (parameterValues.get("first").isBlank()) {
            throw new InvalidCommandException("Please tell me the task name.");
        }
        if (parameterValues.get("/by").isBlank()) {
            throw new InvalidCommandException("Please tell me when the task is due.");
        }

        String name = parameterValues.get("first");
        String dueDateText = parameterValues.get("/by");

        try {
            LocalDate dueDate = LocalDate.parse(dueDateText, formatter);

            return new Deadline(name, dueDate);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Please enter the date in the following format: DD-MM-YYYY");
        }
    }
}
