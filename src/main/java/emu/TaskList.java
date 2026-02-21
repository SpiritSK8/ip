package emu;

import java.util.ArrayList;

/**
 * Contains a list of tasks and provides methods
 * to manage and access them
 */
public class TaskList {
    private static final int TASKLIST_STARTING_POINT = 0;

    private final ArrayList<Task> tasks;
    private final ArrayList<String> history;

    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "tasks should not be null";
        this.tasks = tasks;
        this.history = new ArrayList<>();
    }

    public Task getTask(int position) throws EmuException {
        if (position < tasks.size() && position >= TASKLIST_STARTING_POINT) {
            Task task = tasks.get(position);
            assert task != null : "task should not be null";
            return task;
        } else {
            throw new EmuException("Umm... I don't think that task is real!");
        }
    }

    public int size() {
        return tasks.size();
    }

    public String listTasks() {
        String temp = "Okay, here's your tasks!";
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            temp += "\n" + i + ". " + task.toString();
        }
        if (tasks.isEmpty()) {
            temp = "Hmm... it looks like you don't have any tasks yet!";
        }
        return temp;
    }

    public String findTasks(String search) {
        assert search != null : "search must not be null";

        int counter = 0;
        String temp = "Let me see... these kinda match:";
        for (Task task : tasks) {
            if (task.getDescription().contains(search)) {
                counter++;
                temp += "\n" + counter + ". " + task.toString();
            }
        }
        if (counter == 0) {
            temp = "Uh... I didn't find anything matching that.";
        }
        return temp;
    }

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

    public String addToDoTask(String desc) {
        assert desc != null && !desc.isEmpty() : "desc cannot be null or empty";

        ToDo task = new ToDo(desc);
        tasks.add(task);
        history.add("add");
        return "Oh! I added this To-Do thing:\n"
                + "    " + task.toString() + "\n"
                + "Isn't that cool?";
    }

    public String addDeadlineTask(String desc, String by) {
        assert desc != null && !desc.isEmpty() : "desc cannot be null or empty";
        assert by != null && !by.isEmpty() : "by cannot be null or empty";

        Deadline task = new Deadline(desc, by);
        tasks.add(task);
        history.add("add");
        return "Okay! This Deadline is in:\n"
                + "    " + task.toString() + "\n"
                + "Hmm... hope that's right!";
    }

    public String addEventTask(String desc, String from, String to) {
        assert desc != null && !desc.isEmpty() : "desc cannot be null or empty";
        assert from != null && !from.isEmpty() : "from cannot be null or empty";
        assert to != null && !to.isEmpty() : "to cannot be null or empty";

        Event task = new Event(desc, from, to);
        tasks.add(task);
        history.add("add");
        return "Ooh! I put in this Event:\n"
                + "    " + task.toString() + "\n"
                + "Hehe, nice!";
    }

    public String deleteTask(int position) throws EmuException {
        Task task = getTask(position - 1);
        tasks.remove(position - 1);
        history.add("delete " + task.toStorageString());
        return "Eek! I took this one away:\n"
                + "    " + task.toString() + "\n"
                + "It's gone now!";
    }

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
                response += "\nI removed the last task I added: " + task.toString();
            }
            case "unmark" -> {
                int position = Parser.parseNumber(other);
                Task task = tasks.get(position - 1);
                task.markComplete();
                response += "\nI marked it again: " + task.toString();
            }
            case "mark" -> {
                int position = Parser.parseNumber(other);
                Task task = tasks.get(position - 1);
                task.markIncomplete();
                response += "\nI unmarked it again: " + task.toString();
            }
            case "delete" -> {
                Task task = Storage.parseTask(other);
                tasks.add(task);
                response += "\nI brought back this task at the end of your list: " + task.toString();
            }
            default -> throw new EmuException("Erm... I'm not sure what to undo!");
        }
        return response;
    }
}