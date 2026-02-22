package myne.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import myne.MyneFace;
import myne.Myne;
import myne.TaskList;
import myne.TaskStorage;
import myne.task.Event;

/**
 * A class to encapsulate the logic for adding an <code>Event</code> and parsing the command for doing so.
 */
public class AddEventCommand implements Command {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "[yyyy-M[M]-d[d]][d[d]-M[M]-yyyy][d MMM yyyy]");

    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    /**
     * Creates a command that, when calling <code>execute()</code>,
     * will add an <code>Event</code> task to the specified Myne instance.
     * @param myne Instance of Myne.
     * @param parameters Command parameters in the format of <code>&lt;name&gt;
     *                   /from &lt;date in DD-MM-YYYY format&gt;
     *                   /to &lt;date in DD-MM-YYYY format&gt;</code>.
     *                   For example, "Tour de France /from 15-3-2026 /to 21-3-2026".
     *
     */
    public AddEventCommand(Myne myne, String parameters) {
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    /**
     * Adds the <code>Event</code> to Myne's task list and saves it to the file.
     * @throws InvalidCommandException If the parameters provided in the constructor do not match the format.
     */
    @Override
    public Response execute() throws InvalidCommandException {
        // Add task and save.
        Event event = parseCommand(parameters);
        taskList.add(event);
        storage.saveTasks(taskList);

        return new Response("I entrust you with this task.\n\n" + event.toString(),
                Status.SUCCESS,
                MyneFace.MYNE_DEFAULT,
                Myne.MYNE_NAME);
    }

    private Event parseCommand(String parameters) throws InvalidCommandException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException("Please provide the task details.", MyneFace.MYNE_WORRIED, Myne.MYNE_NAME);
        }

        HashMap<String, String> parameterValues = CommandParser.extractParameters(parameters);

        // Checks for missing /from or /to
        if (!parameterValues.containsKey("/from") || !parameterValues.containsKey("/to")) {
            throw new InvalidCommandException(
                    "Please provide the dates with /from and /to.", MyneFace.MYNE_DEFAULT, Myne.MYNE_NAME);
        }

        // Checks for missing name, start date, or end date.
        if (parameterValues.get("first").isBlank()) {
            throw new InvalidCommandException(
                    "What is this event named?", MyneFace.MYNE_CONFUSED, Myne.MYNE_NAME);
        }
        if (parameterValues.get("/from").isBlank()) {
            throw new InvalidCommandException(
                    "When can I expect this event to start?", MyneFace.MYNE_HAPPY, Myne.MYNE_NAME);
        }
        if (parameterValues.get("/to").isBlank()) {
            throw new InvalidCommandException(
                    "I need to know when the event ends.", MyneFace.MYNE_DISGUSTED, Myne.MYNE_NAME);
        }

        String name = parameterValues.get("first");
        String fromText = parameterValues.get("/from");
        String toText = parameterValues.get("/to");

        try {
            LocalDate from = LocalDate.parse(fromText, formatter);
            LocalDate to = LocalDate.parse(toText, formatter);

            return new Event(name, from, to);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("There is a mistake in your date. Use this format: DD-MM-YYYY",
                    MyneFace.MYNE_WORRIED,
                    Myne.MYNE_NAME);
        }
    }
}
