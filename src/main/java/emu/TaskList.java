package emu;

import java.util.ArrayList;

/**
 * Contains a list of tasks and provides methods to manage and access them.
 */
public class TaskList {
    private static final int TASKLIST_STARTING_POINT = 0;

    private final ArrayList<Task> tasks;
    private final ArrayList<String> history;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "tasks must not be null";
        this.tasks = tasks;
        this.history = new ArrayList<>();
    }

    /**
     * Returns the task at the given position in the TaskList.
     *
     * @param position The position of the task in the TaskList (0-based).
     * @return The requested task.
     * @throws EmuException If the position is invalid.
     */
    public Task getTask(int position) throws EmuException {
        if (position < tasks.size() && position >= TASKLIST_STARTING_POINT) {
            Task task = tasks.get(position);
            assert task != null : "task must not be null";
            return task;
        } else {
            throw new EmuException("Umm... I don't think that task is real!");
        }
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a string representation of all tasks in the TaskList.
     *
     * @return A formatted string listing all tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Hmm... it looks like you don't have any tasks yet!";
        }

        String temp = "Okay, here's your tasks!";
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            temp += "\n" + i + ". " + task.toString();
        }
        return temp;
    }

    /**
     * Searches for tasks containing the given substring and returns a string
     * listing the matches.
     *
     * @param substring Substring to look for in task descriptions.
     * @return A string of matching tasks.
     */
    public String findTasks(String substring) {
        assert substring != null : "substring must not be null";

        int counter = 0;
        String temp = "Let me see... these kinda match:";
        for (Task task : tasks) {
            if (task.getDescription().contains(substring)) {
                counter++;
                temp += "\n" + counter + ". " + task.toString();
            }
        }
        if (counter == 0) {
            temp = "Uh... I didn't find anything matching that.";
        }
        return temp;
    }

    /**
     * Marks the task at the given position as complete and returns a string
     * showing the marked task.
     *
     * @param position The 1-based position of the task in the TaskList.
     * @return Response string after marking the task.
     * @throws EmuException If the task is already marked.
     */
    public String markTask(int position) throws EmuException {
        Task task = getTask(position - 1);
        if (task.getStatusIcon().equals(" ")) {
            task.markComplete();
            history.add("mark " + position);
            return "Ta-da! I marked it as done:\n"
                    + "    " + task.toString();
        } else {
            throw new EmuException("Oh! It's already marked, silly!");
        }
    }

    /**
     * Marks the task at the given position as incomplete and returns a string
     * showing the unmarked task.
     *
     * @param position The 1-based position of the task in the TaskList.
     * @return Response string after unmarking the task.
     * @throws EmuException If the task is already unmarked.
     */
    public String unmarkTask(int position) throws EmuException {
        Task task = getTask(position - 1);
        if (task.getStatusIcon().equals("X")) {
            task.markIncomplete();
            history.add("unmark " + position);
            return "Right then! I unmarked it:\n"
                    + "    " + task.toString() + "\n"
                    + "Hehe, there you go!";
        } else {
            throw new EmuException("Eh? It's already unmarked, I think!");
        }
    }

    /**
     * Adds a ToDo task with the given description and returns a string
     * showing the added task.
     *
     * @param desc Description of the ToDo task.
     * @return Response string after adding the task.
     */
    public String addToDoTask(String desc) {
        assert desc != null && !desc.isEmpty() : "desc must not be null or empty";

        ToDo task = new ToDo(desc);
        tasks.add(task);
        history.add("add");
        return "Oh! I added this To-Do thing:\n"
                + "    " + task.toString() + "\n"
                + "Isn't that cool?";
    }

    /**
     * Adds a Deadline task with the given description and by date and returns
     * a string showing the added task.
     *
     * @param description Description of the Deadline task.
     * @param by By date/string of the Deadline task.
     * @return Response string after adding the task.
     */
    public String addDeadlineTask(String description, String by) {
        assert description != null && !description.isEmpty()
                : "description must not be null or empty";
        assert by != null && !by.isEmpty() : "by must not be null or empty";

        Deadline task = new Deadline(description, by);
        tasks.add(task);
        history.add("add");
        return "Okay! This Deadline is in:\n"
                + "    " + task.toString() + "\n"
                + "Hmm... hope that's right!";
    }

    /**
     * Adds an Event task with the given description, from date, and to date and
     * returns a string showing the added task.
     *
     * @param description Description of the Event task.
     * @param from From date/string of the Event task.
     * @param to To date/string of the Event task.
     * @return Response string after adding the task.
     */
    public String addEventTask(String description, String from, String to) {
        assert description != null && !description.isEmpty()
                : "description must not be null or empty";
        assert from != null && !from.isEmpty() : "from must not be null or empty";
        assert to != null && !to.isEmpty() : "to must not be null or empty";

        Event task = new Event(description, from, to);
        tasks.add(task);
        history.add("add");
        return "Ooh! I put in this Event:\n"
                + "    " + task.toString() + "\n"
                + "Hehe, nice!";
    }

    /**
     * Deletes the task at the given position and returns a string showing the
     * deleted task.
     *
     * @param position The 1-based position of the task in the TaskList.
     * @return Response string after deleting the task.
     * @throws EmuException If the position is invalid.
     */
    public String deleteTask(int position) throws EmuException {
        Task task = getTask(position - 1);
        tasks.remove(position - 1);
        history.add("delete " + task.toStorageString());
        return "Eek! I took this one away:\n"
                + "    " + task.toString() + "\n"
                + "It's gone now!";
    }

    /**
     * Undoes the previous edit to the TaskList and returns a string showing
     * the undone task.
     *
     * @return Response string representing the undone task.
     * @throws EmuException If there is nothing to undo.
     */
    public String undoLastCommand() throws EmuException {
        if (history.isEmpty()) {
            throw new EmuException("Hmm... I can't undo anything right now!");
        }

        String lastCommand = history.remove(history.size() - 1);
        String[] parts = lastCommand.split(" ", 2);
        String command = parts[0];
        String other = parts.length > 1 ? parts[1] : "";

        String response = "Oh! Let's undo that then...";

        switch (command) {
        case "add" -> {
            Task task = tasks.get(tasks.size() - 1);
            tasks.remove(tasks.size() - 1);
            response += "\nI removed the last task I added:\n" + task.toString();
        }
        case "unmark" -> {
            int position = Parser.parseNumber(other);
            Task task = tasks.get(position - 1);
            task.markComplete();
            response += "\nI marked it again:\n" + task.toString();
        }
        case "mark" -> {
            int position = Parser.parseNumber(other);
            Task task = tasks.get(position - 1);
            task.markIncomplete();
            response += "\nI unmarked it again:\n" + task.toString();
        }
        case "delete" -> {
            Task task = Storage.readStorageTask(other);
            tasks.add(task);
            response += "\nI brought back this task at the end of your list:\n" + task.toString();
        }
        default -> throw new EmuException("Erm... I'm not sure what to undo!");
        }

        return response;
    }
}