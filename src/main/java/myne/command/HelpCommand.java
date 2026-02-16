package myne.command;

import myne.Myne;

/**
 * Command to show Myne help.
 */
public class HelpCommand implements Command {
    private static final String HELP = """
            These are the commands that you may enter. Please ensure that each is used correctly.
            
            help: Shows this help message.
            bye: Farewell.
            list: Shows all tasks.
            mark <taskNumber>: Marks the specified task as done.
            unmark <taskNumber>: Marks the specified task as done.
            todo <taskName>: Creates a new task without deadline.
            deadline <taskName> /by <dueDate>: Creates a task with a deadline (dd-MM-yyyy).
            event <taskName> /from <startDate> /to <endDate>: Creates an event with start and end dates (dd-MM-yyyy).
            delete <taskNumber>: Deletes the specified task.
            find <keyword>: Finds all the tasks containing the keyword.""";

    /**
     * Gives a list of commands for Myne.
     */
    @Override
    public Response execute() {
        return new Response(HELP, Status.SUCCESS);
    }
}
