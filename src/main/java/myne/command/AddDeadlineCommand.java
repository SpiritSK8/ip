package myne.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import myne.Myne;
import myne.MyneException;
import myne.MyneFace;
import myne.TaskList;
import myne.TaskStorage;
import myne.User;
import myne.task.Deadline;

/**
 * A class to encapsulate the logic for adding a <code>Deadline</code> and parsing the command for doing so.
 */
public class AddDeadlineCommand implements Command {
    private static final String USAGE = """
            Usage:
            deadline <task_name> /by <due_date>
            (DD-MM-YYYY)""";

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[yyyy-M[M]-d[d]]" +
                    "[d[d]-M[M]-yyyy]" +
                    "[d[d] MMM yyyy]")
            .toFormatter();

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
     * @throws MyneException If the date provided is in the past.
     */
    @Override
    public Response execute() throws MyneException {
        Deadline deadline = parseCommand(parameters);
        taskList.add(deadline);
        storage.saveTasks(taskList);

        return new Response("Carry out this task.\n\n" + deadline.toString(),
                Status.SUCCESS,
                MyneFace.FERDINAND_DEFAULT,
                User.FERDINAND);
    }

    private Deadline parseCommand(String parameters) throws MyneException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException(
                    "Nothing is there, fool.\n\n" + USAGE, MyneFace.FERDINAND_EXASPERATED, User.FERDINAND);
        }

        HashMap<String, String> parameterValues = CommandParser.extractParameters(parameters);

        if (!parameterValues.containsKey("/by")) {
            throw new InvalidCommandException(
                    "Provide the due date with /by.\n\n" + USAGE, MyneFace.FERDINAND_DEFAULT, User.FERDINAND);
        }

        if (parameterValues.get("first").isBlank()) {
            throw new InvalidCommandException(
                    "You are missing the task name.", MyneFace.FERDINAND_EXASPERATED, User.FERDINAND);
        }
        if (parameterValues.get("/by").isBlank()) {
            throw new InvalidCommandException(
                    "You are missing the due date.", MyneFace.FERDINAND_EXASPERATED, User.FERDINAND);
        }

        String name = parameterValues.get("first");
        String dueDateText = parameterValues.get("/by");

        try {
            LocalDate dueDate = LocalDate.parse(dueDateText, FORMATTER);

            if (dueDate.isBefore(LocalDate.now())) {
                throw new MyneException("That day is past us.", MyneFace.FERDINAND_EXASPERATED, User.FERDINAND);
            }

            return new Deadline(name, dueDate);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(
                    "Your date is improper. Change it to DD-MM-YYYY, YYYY-MM-DD, or DD MMM YYYY.",
                    MyneFace.FERDINAND_EXASPERATED,
                    User.FERDINAND);
        }
    }
}
