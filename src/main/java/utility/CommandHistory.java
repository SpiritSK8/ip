package utility;

import java.util.ArrayList;

public class CommandHistory {
    private final ArrayList<String> commands;
    private int currentIndex;
    private String draft;

    /**
     * Creates a command history. The command history works similarly to a conventional terminal's command history.
     * Recorded commands will be added to the end of the list,
     * and duplicate commands won't be added twice in a row.<br><br>
     *
     * Example usage:
     * <pre>
     * {@code
     * CommandHistory ch = new CommandHistory();
     * ch.add("first command");
     * ch.add("second command");
     *
     * String userDraft = "draft command"; // Assume the user is currently editing userDraft.
     * userDraft = ch.prevCommand(userDraft); // userDraft becomes "second command".
     * userDraft = ch.prevCommand(userDraft); // userDraft becomes "first command".
     * userDraft = ch.nextCommand(userDraft); // userDraft becomes "second command".
     * userDraft = ch.nextCommand(userDraft); // userDraft becomes "draft command".
     * }
     * </pre>
     */
    public CommandHistory() {
        this.commands = new ArrayList<>();
        resetIndexAndDraft();
    }

    /**
     * Adds a command to the command history. Duplicate commands won't be added twice in a row.
     * When adding a command, the current command position will be reset to the end of the list,
     * i.e. After adding a command, <code>prevCommand()</code> will return the last added command.
     * @param command the command.
     */
    public void add(String command) {
        if (commands.isEmpty() || !commands.get(commands.size() - 1).equals(command)) {
            // Duplicate commands won't be added twice in a row.
            commands.add(command);
        }
        resetIndexAndDraft();
    }

    /**
     * Shifts to the next command in the history, or the draft if the end of history is reached.
     * @param currentCommand the current command that is being edited by the user. Used to save as draft.
     * @return the next command in the history.
     */
    public String nextCommand(String currentCommand) {
        if (isOnDraft()) {
            // If the user is currently editing the draft, then nextCommand() shouldn't erase the draft.
            return currentCommand;
        }
        currentIndex++; // isOnDraft() already ensures that currentIndex < commands.size().
        return get(currentIndex);
    }

    /**
     * Shifts to the previous command in the history, or the draft if the history is empty.
     * @param currentCommand the current command that is being edited by the user. Used to save as draft.
     * @return the previous command in the history.
     */
    public String prevCommand(String currentCommand) {
        if (isOnDraft()) {
            draft = currentCommand; // Saves the current draft.
        }
        if (currentIndex > 0) {
            currentIndex--;
        }
        return get(currentIndex);
    }

    /**
     * Returns the command at index. If <code>i == commands.size()</code>, returns the draft.
     * @param i the index of the command.
     * @return the command at position <code>i</code>.
     */
    private String get(int i) {
        if (i == commands.size() || commands.isEmpty()) {
            return draft;
        }
        return commands.get(i);
    }

    /**
     * Returns true if <code>currentIndex == commands.size()</code>. In other words, returns true if the current
     * index is referring to the draft.
     * @return <code>true</code> if the current command is the draft.
     */
    private boolean isOnDraft() {
        return currentIndex == commands.size();
    }

    /**
     * Resets the index to <code>commands.size()</code> and empties the draft.
     */
    private void resetIndexAndDraft() {
        currentIndex = commands.size();
        this.draft = "";
    }
}
