package myne.command;

import myne.MyneFace;
import myne.Myne;

/**
 * Command to show Myne help.
 */
public class HelpCommand implements Command {
    private static final String HELP = """
            For efficient communication, we will be using the following commands. \
            Anything else will be ignored, so memorize them well.
            
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
            
            Type "help <command_name>" to learn more about a command.""";

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
        return getHelpFor(commandType);
    }

    private Response getHelpFor(String commandType) {
        return switch (commandType) {
            case "" -> getDefaultHelp();
            case "help" -> getHelpHelp();
            case "bye" -> getByeHelp();
            case "todo" -> getTodoHelp();
            case "deadline" -> getDeadlineHelp();
            case "event" -> getEventHelp();
            case "list" -> getListHelp();
            case "mark" -> getMarkHelp();
            case "unmark" -> getUnmarkHelp();
            case "delete" -> getDeleteHelp();
            case "find" -> getFindHelp();
            default -> getUnknownHelp();
        };
    }

    private Response getDefaultHelp() {
        return new Response(HELP, Status.SUCCESS, MyneFace.FERDINAND_DEFAULT, Myne.FERDINAND_NAME);
    }

    private Response getHelpHelp() {
        String help = """
                Use this command to read the manual.
                You can also learn more about individual commands by adding the command name after "help".
                
                Usage:
                help
                help <command_name>""";

        return new Response(help, Status.SUCCESS, MyneFace.MYNE_WONDER, Myne.MYNE_NAME);
    }

    private Response getByeHelp() {
        String help = """
                Use this command if you wish to depart.
                
                Usage:
                bye""";

        return new Response(help, Status.SUCCESS, MyneFace.MYNE_THANKFUL, Myne.MYNE_NAME);
    }

    private Response getTodoHelp() {
        String help = """
                Use this command to add a new task.
                
                Usage:
                todo <task_name>
                
                Example usage:
                todo read books""";

        return new Response(help, Status.SUCCESS, MyneFace.MYNE_DEFAULT, Myne.MYNE_NAME);
    }

    private Response getDeadlineHelp() {
        String help = """
                This command adds a new task with a due date.
                
                Usage:
                deadline <task_name> /by <due_date>
                
                Example usage:
                deadline buy books /by 14-3-2026
                deadline read books /by 5 may 2026""";

        return new Response(help, Status.SUCCESS, MyneFace.FERDINAND_DEFAULT, Myne.FERDINAND_NAME);
    }

    private Response getEventHelp() {
        String help = """
                Use this command to add a new event with a start and end date.
                How exciting!
                
                Usage:
                event <task_name> /from <start_date> /to <end_date>
                
                Example usage:
                event read books /from 14-3-2026 /to 15 mar 2026""";

        return new Response(help, Status.SUCCESS, MyneFace.MYNE_WONDER, Myne.MYNE_NAME);
    }

    private Response getListHelp() {
        String help = """
                Use this command to list your tasks.
                You... didn't forget your tasks again, right?
                
                Usage:
                list""";

        return new Response(help, Status.SUCCESS, MyneFace.MYNE_WORRIED, Myne.MYNE_NAME);
    }

    private Response getMarkHelp() {
        String help = """
                Use this command to mark a task as done.
                One task down!
                
                Usage:
                mark <task_number>
                mark <task_name>
                
                Example usage:
                mark 1
                mark read books""";

        return new Response(help, Status.SUCCESS, MyneFace.MYNE_JOYFUL, Myne.MYNE_NAME);
    }

    private Response getUnmarkHelp() {
        String help = """
                This command unmarks a task.
                
                Usage:
                unmark <task_number>
                unmark <task_name>
                
                Example usage:
                unmark 1
                unmark read books""";

        return new Response(help, Status.SUCCESS, MyneFace.FERDINAND_EXASPERATED, Myne.FERDINAND_NAME);
    }

    private Response getDeleteHelp() {
        String help = """
                Use this command to delete a task.
                If you can't handle it, we can always give it to another person...
                
                Usage:
                delete <task_number>
                delete <task_name>
                
                Example usage:
                delete 1
                delete read books""";

        return new Response(help, Status.SUCCESS, MyneFace.MYNE_WORRIED, Myne.MYNE_NAME);
    }

    private Response getFindHelp() {
        String help = """
                Use this command to find your tasks.
                I'll get to read through your tasks!
                
                Usage:
                find <task_name>
                
                Example usage:
                find book
                find oo""";

        return new Response(help, Status.SUCCESS, MyneFace.MYNE_JOYFUL, Myne.MYNE_NAME);
    }

    private Response getUnknownHelp() {
        String help = """
                I do not recognize that command. Please type "help" to look at the list of commands.""";

        return new Response(help, Status.SUCCESS, MyneFace.MYNE_DEFAULT, Myne.MYNE_NAME);
    }
}
