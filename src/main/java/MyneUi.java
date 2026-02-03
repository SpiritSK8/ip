import java.util.Scanner;

public class MyneUi {
    private final Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the divider line (--------).
     */
    public void showDivider() {
        System.out.println("________________________________________");
    }

    /**
     * Shows the greeting for when Myne is started.
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
     * Shows an error message.
     * @param message The error message.
     */
    public void showError(String message) {
        showDivider();
        System.out.println(message);
        showDivider();
    }

    /**
     * Shows an exit message.
     */
    public void showExit() {
        showDivider();
        System.out.println("Farewell. May the time come when our threads of fate are woven together again.");
        showDivider();
    }
}
