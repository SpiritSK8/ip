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
            case "todo" -> getTodoHelp();
            case "deadline" -> getDeadlineHelp();
            case "event" -> getEventHelp();
            default -> HELP;
        };
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
}
