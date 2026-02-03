import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddDeadlineCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;
    private final TaskStorage storage;
    private final String parameters;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-M[M]-d[d]][d[d]-M[M]-yyyy][d MMM yyyy]");

    public AddDeadlineCommand(Myne myne, String parameters) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
        this.storage = myne.getStorage();
        this.parameters = parameters;
    }

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
            throw new InvalidCommandException("Deadline description cannot be empty.");
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
