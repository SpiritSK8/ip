package emu;

import java.util.ArrayList;

/**
 * Coordinates the UI, TaskList, Parser, and Storage components
 * to handle user commands and program flow
 */
public class Emu {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit; // Indicates whether the chatbot should exit
    private boolean hasStorageFailed; // Tracks if storage failed to initialise

    /**
     * Initialises the storage, UI, and TaskList
     * If storage cannot be created, sets hasStorageFailed to true and continues
     */
    public Emu() {
        this.ui = new Ui();
        this.tasks = new TaskList(new ArrayList<Task>());
        this.isExit = false;

        try {
            this.storage = new Storage("./data/tasks.txt");
        } catch (EmuException e) {
            this.storage = null;
            this.hasStorageFailed = true;
        }
    }

    /**
     * Initialises the TaskList by loading from storage
     * If loading storage fails, hasStorageFailed set to true
     *
     * @return Greeting string, with warning if storage failed
     */
    public String initialiseTaskList() {
        try {
            this.tasks = storage.initialiseList();
        } catch (EmuException e) {
            this.hasStorageFailed = true;
        }
        return ui.formatResponse(ui.giveGreeting(this.hasStorageFailed));
    }

    /**
     * Returns whether the chatbot is set to exit
     */
    public boolean getExitStatus() {
        return this.isExit;
    }

    /**
     * Calls for the command to be executed based on user input
     * and returns a formatted response
     *
     * @param input User input string
     * @return Formatted response string
     */
    public String respond(String input) {
        assert input != null : "input should not be null";

        // Parses the input into command and other portions
        Parser parts = new Parser(input);

        try {
            return ui.formatResponse(executeCommand(parts.getCommand(), parts.getOther()));
        } catch (EmuException e) {
            return ui.formatResponse(e.getMessage());
        }
    }

    /**
     * Handles the execution of a command based on its type and argument
     *
     * @param command The command string
     * @param other The argument string for the command
     * @return The result string from executing the command
     * @throws EmuException If the command is invalid or fails
     */
    private String executeCommand(String command, String other) throws EmuException {
        return switch (command) {
        case "bye" -> {
            if (storage != null) {
                storage.resetFile(tasks);
            }
            isExit = true;
            yield "Bye!! Have a WONDERHOY day!";
        }
        case "list" -> tasks.listTasks();
        case "find" -> tasks.findTasks(other);
        case "mark" -> tasks.markTask(Parser.parseNumber(other));
        case "unmark" -> tasks.unmarkTask(Parser.parseNumber(other));
        case "todo" -> {
            Parser.verifyTodo(other);
            yield tasks.addToDoTask(other);
        }
        case "deadline" -> {
            String[] details = Parser.parseDeadline(other);
            yield tasks.addDeadlineTask(details[0], details[1]);
        }
        case "event" -> {
            String[] details = Parser.parseEvent(other);
            yield tasks.addEventTask(details[0], details[1], details[2]);
        }
        case "delete" -> tasks.deleteTask(Parser.parseNumber(other));
        case "undo" -> tasks.undoLastCommand();
        default -> throw new EmuException("I don't get what that means!");
        };
    }
}