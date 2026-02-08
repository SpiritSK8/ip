package myne;

/**
 * A class to encapsulate input/output interaction between Myne and the user.
 */
public class MyneUi {
    /**
     * Returns the greeting for when Myne starts.
     */
    public String getGreetingText() {
        return "Good day to you. My name is Myne.\n"
                + "May our meeting, ordained by the gods be blessed on this fruitful day.";

        // ASCII art is kept for the memories.
        //                """
        //                Good day to you. My name is
        //                ___  ___
        //                |  \\/  |
        //                | .  . |_   _ _ __   ___
        //                | |\\/| | | | | '_ \\ / _ \\
        //                | |  | | |_| | | | |  __/
        //                \\_|  |_/\\__, |_| |_|\\___|
        //                         __/ |
        //                        |___/
        //                May our meeting, ordained by the gods be blessed on this fruitful day.""";
    }

    /**
     * Shows the exit message.
     */
    public String getFarewellText() {
        return "Farewell. May the time come when our threads of fate are woven together again.";
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

        StringBuilder sb = new StringBuilder(initialMessage).append("\n");
        for (int i = 0; i < taskList.size() - 1; i++) {
            sb.append("  ").append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        sb.append("  ")
                .append(taskList.size())
                .append(".")
                .append(taskList.get(taskList.size() - 1)); // Last line doesn't need line break.

        return sb.toString();
    }
}
