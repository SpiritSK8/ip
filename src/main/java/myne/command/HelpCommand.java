package myne.command;

/**
 * Command to show Myne help.
 */
public class HelpCommand implements Command {
    private static final String HELP = """
            These are the commands that you may enter.
            
            help
            bye
            todo
            deadline
            event
            list
            mark
            unmark
            delete
            find
            
            Please type "help <command_name>" to learn more about a command.""";

    private final String parameters;

    public HelpCommand(String parameters) {
        this.parameters = parameters;
    }

    /**
     * Gives a list of commands for Myne.
     */
    @Override
    public Response execute() {
        String commandType = parameters.trim();
        String helpMessage = getHelpFor(commandType);

        return new Response(helpMessage, Status.SUCCESS);
    }

    private String getHelpFor(String commandType) {
        return switch (commandType) {
            case "bye" -> getByeHelp();
            case "todo" -> getTodoHelp();
            case "deadline" -> getDeadlineHelp();
            case "event" -> getEventHelp();
            case "list" -> getListHelp();
            case "mark" -> getMarkHelp();
            case "unmark" -> getUnmarkHelp();
            case "delete" -> getDeleteHelp();
            case "find" -> getFindHelp();
            default -> HELP;
        };
    }

    private String getByeHelp() {
        return """
                For when you wish to depart.
                
                Usage:
                bye""";
    }

    private String getTodoHelp() {
        return """
                Adds a new task with the specified task_name.
                
                Usage:
                todo <task_name>
                
                Example usage:
                todo read books""";
    }

    private String getDeadlineHelp() {
        return """
                Adds a new task with a deadline.
                
                Usage:
                deadline <task_name> /by <due_date>
                
                Example usage:
                deadline buy books /by 14-3-2026
                deadline read books /by 5 may 2026""";
    }

    private String getEventHelp() {
        return """
                Adds a new task with a start and end date.
                
                Usage:
                event <task_name> /from <start_date> /to <end_date>
                
                Example usage:
                event read books /from 14-3-2026 /to 15 mar 2026""";
    }

    private String getListHelp() {
        return """
                Lists all of the tasks that you have.
                
                Usage:
                list""";
    }

    private String getMarkHelp() {
        return """
                Marks the specified task as done.
                
                Usage:
                mark <task_number>
                
                Example usage:
                mark 1""";
    }

    private String getUnmarkHelp() {
        return """
                Marks the specified task as not done.
                
                Usage:
                unmark <task_number>
                
                Example usage:
                unmark 1""";
    }

    private String getDeleteHelp() {
        return """
                Deletes the specified task.
                
                Usage:
                delete <task_number>
                
                Example usage:
                delete 1""";
    }

    private String getFindHelp() {
        return """
                Finds all the tasks that match the keyword.
                
                Usage:
                find <keyword>
                
                Example usage:
                find book""";
    }
}
