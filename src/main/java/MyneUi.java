import java.util.List;

public class MyneUi {

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
     * Shows a message when task is successfully added.
     * @param task The task that was added to the list.
     */
    public void showAddTask(Task task) {
        showDivider();
        System.out.println("I entrust you with this task.");
        System.out.println("  " + task.toString());
        showDivider();
    }

    /**
     * Shows a message when the user attempts to list the task but it is empty.
     */
    public void showEmptyList() {
        showDivider();
        System.out.println("Hm... It appears you are under-worked. Shall we remedy that?");
        showDivider();
    }

    /**
     * Shows a message when the user asks to list the tasks.
     * @param taskList The task list.
     */
    public void showList(List<Task> taskList) {
        showDivider();
        System.out.println("Behold, your tasks!");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("  " + (i + 1) + "." + taskList.get(i));
        }
        showDivider();
    }

    /**
     * Shows a message after marking a task as completed.
     * @param task The task that was marked.
     */
    public void showMark(Task task) {
        showDivider();
        System.out.println("You have carried out your task with utmost diligence. Very good.");
        System.out.println("  " + task.toString());
        showDivider();
    }

    /**
     * Shows a message after unmarking a task.
     * @param task The task that was marked.
     */
    public void showUnmark(Task task) {
        showDivider();
        System.out.println("Ah, you would like to redo it? Very well.");
        System.out.println("  " + task.toString());
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
     * Shows a message after the user deletes a task.
     * @param removedTask The task that was deleted.
     */
    public void showDelete(Task removedTask) {
        showDivider();
        System.out.println("Let me take that back.");
        System.out.println("  " + removedTask);
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
