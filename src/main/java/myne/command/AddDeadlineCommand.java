package myne.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import myne.MyneFace;
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

        return new Response("Carry out this task.\n\n" + deadline.toString(),
                Status.SUCCESS,
                MyneFace.FERDINAND_DEFAULT,
                Myne.FERDINAND_NAME);
    }

    private Deadline parseCommand(String parameters) throws InvalidCommandException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException(
                    "Nothing is there, fool.", MyneFace.FERDINAND_EXASPERATED, Myne.FERDINAND_NAME);
        }

        HashMap<String, String> parameterValues = CommandParser.extractParameters(parameters);

        // Checks for missing /by
        if (!parameterValues.containsKey("/by")) {
            throw new InvalidCommandException(
                    "Provide the due date with /by.", MyneFace.FERDINAND_DEFAULT, Myne.FERDINAND_NAME);
        }

        // Checks for blank name or due date.
        if (parameterValues.get("first").isBlank()) {
            throw new InvalidCommandException(
                    "Where is the task name?", MyneFace.FERDINAND_EXASPERATED, Myne.FERDINAND_NAME);
        }
        if (parameterValues.get("/by").isBlank()) {
            throw new InvalidCommandException(
                    "When is this task due?", MyneFace.FERDINAND_EXASPERATED, Myne.FERDINAND_NAME);
        }

        String name = parameterValues.get("first");
        String dueDateText = parameterValues.get("/by");

        try {
            LocalDate dueDate = LocalDate.parse(dueDateText, formatter);

            return new Deadline(name, dueDate);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Your date is improper. Change it to DD-MM-YYYY.",
                    MyneFace.FERDINAND_EXASPERATED,
                    Myne.FERDINAND_NAME);
        }
    }
}
