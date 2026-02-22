package myne;

import myne.command.Response;
import myne.command.Status;

/**
 * A class to encapsulate input/output interaction between Myne and the user.
 */
public class MyneUi {
    /**
     * Returns the greeting for when Myne starts.
     */
    public Response getMyneGreeting() {
        String greeting = "Good day to you. My name is Myne. May our meeting be blessed on this fruitful day.";

        return new Response(greeting, Status.SUCCESS, MyneFace.MYNE_THANKFUL, Myne.MYNE_NAME);
    }

    /**
     * Returns the greeting for when Myne starts.
     */
    public Response getFerdinandGreeting() {
        String greeting = """
                I am Ferdinand, Myne's guardian.
                From now on, you will be working under us.
                If you wish to take on a task, simply send word and we will assign it to you.
                
                Type "help" if you need assistance.""";

        return new Response(greeting, Status.SUCCESS, MyneFace.FERDINAND_DEFAULT, Myne.FERDINAND_NAME);
    }

    /**
     * Shows the exit message.
     */
    public Response getFarewell() {
        String farewell = "Farewell. May the time come when our threads of fate are woven together again.";

        return new Response(farewell, Status.SUCCESS, MyneFace.MYNE_THANKFUL, Myne.MYNE_NAME);
    }

    /**
     * Shows the provided task list, along with an initial message.
     * @param taskList The task list to display.
     * @param initialMessage The message prepended before the list.
     */
    public String getTaskListText(TaskList taskList, String initialMessage) {
        if (taskList.isEmpty()) {
            return initialMessage;
        }

        StringBuilder sb = new StringBuilder(initialMessage).append("\n\n");
        for (int i = 0; i < taskList.size() - 1; i++) {
            sb.append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        sb.append(taskList.size())
                .append(".")
                .append(taskList.get(taskList.size() - 1)); // Last line doesn't need line break.

        return sb.toString();
    }
}
