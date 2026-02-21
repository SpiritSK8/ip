package myne.command;

import myne.FerMyneFace;

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
        return getHelpFor(commandType);
    }

    private Response getHelpFor(String commandType) {
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
            default -> getDefaultHelp();
        };
    }

    private Response getDefaultHelp() {
        return new Response(HELP, Status.SUCCESS, FerMyneFace.MYNE_HAPPY);
    }

    private Response getByeHelp() {
        String help = """
                For when you wish to depart.
                
                Usage:
                bye""";

        return new Response(help, Status.SUCCESS, FerMyneFace.MYNE_THANKFUL);
    }

    private Response getTodoHelp() {
        String help = """
                I shall write down a new task for you.
                
                Usage:
                todo <task_name>
                
                Example usage:
                todo read books""";

        return new Response(help, Status.SUCCESS, FerMyneFace.MYNE_DEFAULT);
    }

    private Response getDeadlineHelp() {
        String help = """
                I shall give you a task which must be finished before it's due.
                
                Usage:
                deadline <task_name> /by <due_date>
                
                Example usage:
                deadline buy books /by 14-3-2026
                deadline read books /by 5 may 2026""";

        return new Response(help, Status.SUCCESS, FerMyneFace.FERDINAND_DEFAULT);
    }

    private Response getEventHelp() {
        String help = """
                How exciting! Tell me when the event starts and ends and I will write it down for you.
                
                Usage:
                event <task_name> /from <start_date> /to <end_date>
                
                Example usage:
                event read books /from 14-3-2026 /to 15 mar 2026""";

        return new Response(help, Status.SUCCESS, FerMyneFace.MYNE_WONDER);
    }

    private Response getListHelp() {
        String help = """
                Did you forget your tasks again?
                
                Usage:
                list""";

        return new Response(help, Status.SUCCESS, FerMyneFace.MYNE_WORRIED);
    }

    private Response getMarkHelp() {
        String help = """
                One task down!
                
                Usage:
                mark <task_number>
                mark <keyword>
                
                Example usage:
                mark 1
                mark read books""";

        return new Response(help, Status.SUCCESS, FerMyneFace.MYNE_JOYFUL);
    }

    private Response getUnmarkHelp() {
        String help = """
                Send word when you need to redo a task.
                
                Usage:
                unmark <task_number>
                unmark <keyword>
                
                Example usage:
                unmark 1
                unmark read books""";

        return new Response(help, Status.SUCCESS, FerMyneFace.MYNE_HAPPY);
    }

    private Response getDeleteHelp() {
        String help = """
                Let's give that task to another person...
                
                Usage:
                delete <task_number>
                delete <keyword>
                
                Example usage:
                delete 1
                delete read books""";

        return new Response(help, Status.SUCCESS, FerMyneFace.MYNE_WORRIED);
    }

    private Response getFindHelp() {
        String help = """
                I'll get to read... Eheheh... But of course it is to help you find your task!
                
                Usage:
                find <keyword>
                
                Example usage:
                find book""";

        return new Response(help, Status.SUCCESS, FerMyneFace.MYNE_JOYFUL);
    }
}
