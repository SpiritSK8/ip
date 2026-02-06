package myne;

import java.util.Scanner;

import myne.command.InvalidCommandException;
import myne.task.TaskParser;

/**
 * A class to encapsulate input/output interaction between Myne and the user.
 */
public class MyneUi {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Reads user input from <code>System.in</code>.
     * @return The text that the user inputs.
     * @throws InvalidCommandException If the input contains the task plaintext separator.
     */
    public String readInput() throws InvalidCommandException {
        String input = sc.nextLine();
        if (input.contains(TaskParser.separator())) {
            throw new InvalidCommandException("Commands cannot contain " + TaskParser.separator());
        }
        return input;
    }

    /**
     * Shows the divider line (--------).
     */
    public void showDivider() {
        System.out.println("________________________________________");
    }

    /**
     * Shows the greeting for when Myne starts.
     */
    public void showGreeting() {
        final String greeting =
                """
                Good day to you. My name is
                ___  ___
                |  \\/  |
                | .  . |_   _ _ __   ___
                | |\\/| | | | | '_ \\ / _ \\
                | |  | | |_| | | | |  __/
                \\_|  |_/\\__, |_| |_|\\___|
                         __/ |
                        |___/
                May our meeting, ordained by the gods be blessed on this fruitful day.""";
        showDivider();
        System.out.println(greeting);
        showDivider();
    }

    /**
     * Shows a message sandwiched between 2 divider lines (-----).
     * @param message The message to be shown.
     */
    public void showMessage(String message) {
        showDivider();
        System.out.println(message);
        showDivider();
    }

    /**
     * Shows an error message.
     * @param message The error message.
     */
    public void showError(String message) {
        showDivider();
        System.out.println(message);
        showDivider();
    }

    /**
     * Shows the exit message.
     */
    public void showExit() {
        showDivider();
        System.out.println("Farewell. May the time come when our threads of fate are woven together again.");
        showDivider();
    }

    /**
     * Shows the provided task list, along with an initial message.
     * @param taskList The task list to display.
     * @param initialMessage The message prepended before the list.
     */
    public void showTaskList(TaskList taskList, String initialMessage) {
        if (taskList.isEmpty()) {
            showMessage(initialMessage);
            return;
        }

        StringBuilder sb = new StringBuilder(initialMessage).append("\n");
        for (int i = 0; i < taskList.size() - 1; i++) {
            sb.append("  ").append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        sb.append("  ")
                .append(taskList.size())
                .append(".")
                .append(taskList.get(taskList.size() - 1)); // Last line doesn't need line break.

        showMessage(sb.toString());
    }
}
