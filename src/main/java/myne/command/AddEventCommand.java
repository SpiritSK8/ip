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
import myne.task.Event;

/**
 * A class to encapsulate the logic for adding an <code>Event</code> and parsing the command for doing so.
 */
public class AddEventCommand implements Command {
    private static final String USAGE = """
            Usage:
            event <task_name> /from <start_date> /to <end_date>
            
            Example:
            event Read books /from 25-3-2026 /to 26-3-2026""";

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
     * Adds the <code>Event</code> to Myne's task list and saves it to the file. The starting date will be the earlier
     * of the two, if any.
     * @throws InvalidCommandException If the parameters provided in the constructor do not match the format.
     * @throws MyneException if any of the dates are in the past.
     */
    @Override
    public Response execute() throws MyneException {
        Event event = parseCommand(parameters);
        taskList.add(event);
        storage.saveTasks(taskList);

        return new Response("I entrust you with this task.\n\n" + event.toString(),
                Status.SUCCESS,
                MyneFace.MYNE_DEFAULT,
                User.MYNE);
    }

    private Event parseCommand(String parameters) throws MyneException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException(
                    "Please provide the event details.\n\n" + USAGE, MyneFace.MYNE_WORRIED, User.MYNE);
        }

        HashMap<String, String> parameterValues = CommandParser.extractParameters(parameters);

        if (!parameterValues.containsKey("/from") || !parameterValues.containsKey("/to")) {
            throw new InvalidCommandException(
                    "Please provide the dates with /from and /to.\n\n" + USAGE, MyneFace.MYNE_DEFAULT, User.MYNE);
        }

        if (parameterValues.get("first").isBlank()) {
            throw new InvalidCommandException(
                    "Your event needs a name, doesn't it?", MyneFace.MYNE_CONFUSED, User.MYNE);
        }
        if (parameterValues.get("/from").isBlank()) {
            throw new InvalidCommandException(
                    "Your event needs a starting date.", MyneFace.MYNE_HAPPY, User.MYNE);
        }
        if (parameterValues.get("/to").isBlank()) {
            throw new InvalidCommandException(
                    "I need to know when the event ends.", MyneFace.MYNE_DISGUSTED, User.MYNE);
        }

        String name = parameterValues.get("first");
        String fromText = parameterValues.get("/from");
        String toText = parameterValues.get("/to");

        try {
            LocalDate from = LocalDate.parse(fromText, FORMATTER);
            LocalDate to = LocalDate.parse(toText, FORMATTER);

            if (from.isBefore(LocalDate.now()) || to.isBefore(LocalDate.now())) {
                throw new MyneException("That event is in the past...", MyneFace.MYNE_WORRIED, User.MYNE);
            }

            // Choose whichever date is earlier as the starting date.
            return from.isAfter(to) ? new Event(name, to, from) : new Event(name, from, to);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(
                    "There is a mistake in your date. Use this format: DD-MM-YYYY, YYYY-MM-DD, or DD MMM YYYY.",
                    MyneFace.MYNE_WORRIED,
                    User.MYNE);
        }
    }
}
