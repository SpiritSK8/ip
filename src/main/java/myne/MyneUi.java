package myne;

import java.util.Arrays;
import java.util.List;

import myne.command.HelpCommand;
import myne.command.Response;
import myne.command.Status;

/**
 * A class to encapsulate input/output interaction between Myne and the user.
 */
public class MyneUi {
    /**
     * Returns the greeting for when Myne starts.
     */
    public List<Response> getGreetings() {
        Response greeting1 = new Response(
                "Good day to you. My name is Myne. May our meeting be blessed on this fruitful day.",
                Status.SUCCESS, MyneFace.MYNE_THANKFUL, User.MYNE);

        Response greeting2 = new Response("""
                I am Ferdinand, Myne's guardian. From now on, you will be working under us. \
                Tell us what tasks you want to do, and we will record them.""",
                Status.SUCCESS, MyneFace.FERDINAND_DEFAULT, User.FERDINAND);

        Response greeting3 = new Response("""
                Furthermore, you can use the up and down arrows to scroll through the commands you have sent. \
                How exciting!""",
                Status.SUCCESS, MyneFace.MYNE_WONDER, User.MYNE);

        Response greeting4 = new Response("""
                For now, why don't you start with this command?
                
                todo Read books
                
                That creates a new task for you. You can then mark it with the following command:
                
                mark 1
                
                You can check the tasks that you have using the following command, if you ever forget them.
                
                list
                
                Finally, use the following command if you need help.
                
                help""",
                Status.SUCCESS, MyneFace.MYNE_HAPPY, User.MYNE);

        Response[] greetings = new Response[] {greeting1, greeting2, greeting3, greeting4};
        return Arrays.stream(greetings).toList();
    }

    /**
     * Shows the exit message.
     */
    public Response getFarewell() {
        String farewell = "Farewell. May the time come when our threads of fate are woven together again.";

        return new Response(farewell, Status.SUCCESS, MyneFace.MYNE_THANKFUL, User.MYNE);
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
