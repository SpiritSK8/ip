package myne.command;

import myne.Myne;
import myne.MyneUi;
import myne.TaskList;
import myne.TaskStorage;

import myne.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class to encapsulate the logic for adding an <code>Event</code> and parsing the command for doing so.
 */
public class AddEventCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "[yyyy-M[M]-d[d]][d[d]-M[M]-yyyy][d MMM yyyy]");

    /**
     * Creates a command that, when calling <code>execute()</code>, will add an <code>Event</code> task to the specified Myne instance.
     * @param myne Instance of Myne.
     * @param parameters Command parameters in the format of <code>&lt;name&gt; /from &lt;date in DD-MM-YYYY format&gt;
     *                   /to &lt;date in DD-MM-YYYY format&gt;</code>. For example, "Tour de France /from 15-3-2026 /to 21-3-2026".
     *
     */
    public AddEventCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

    /**
     * Adds the <code>Event</code> to Myne's task list and saves it to the file.
     * @throws InvalidCommandException If the parameters provided in the constructor do not match the format.
     */
    @Override
    public void execute() throws InvalidCommandException {
        // Add task and save.
        Event event = parseCommand(parameters);
        taskList.add(event);
        storage.saveTasks(taskList);

        // Show message.
        ui.showMessage("I entrust you with this task.\n  " + event.toString());
    }

    private Event parseCommand(String parameters) throws InvalidCommandException {
        if (parameters.isEmpty()) {
            throw new InvalidCommandException("Event description cannot be empty.");
        }
        if (!parameters.contains("/from") || !parameters.contains("/to")) {
            throw new InvalidCommandException("Events must have a /from and /to.");
        }

        String[] parts = parameters.split("/from");

        if (parts.length < 2) {
            throw new InvalidCommandException("Missing description, start time, or end time.");
        }

        String name = parts[0].trim();

        parts = parts[1].split("/to");

        if (parts.length < 2) {
            throw new InvalidCommandException("Missing description, start time, or end time.");
        }

        String fromText = parts[0].trim();
        String toText = parts[1].trim();

        if (name.isEmpty() || fromText.isEmpty() || toText.isEmpty()) {
            throw new InvalidCommandException("Missing description, start time, or end time.");
        }

        LocalDate from = LocalDate.parse(fromText, formatter);
        LocalDate to = LocalDate.parse(toText, formatter);

        return new Event(name, from, to);
    }
}
