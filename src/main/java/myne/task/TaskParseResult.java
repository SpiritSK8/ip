package myne.task;

import java.util.ArrayList;

/**
 * A class to encapsulate the result of parsing a task file.
 * The result contains a list of tasks and a list of error messages.
 */
public class TaskParseResult {
    private final ArrayList<Task> taskList;
    private final ArrayList<String> errorList;

    public TaskParseResult() {
        this.taskList = new ArrayList<>();
        this.errorList = new ArrayList<>();
    }

    public boolean hasError() {
        return !errorList.isEmpty();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void addError(String message) {
        errorList.add(message);
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public ArrayList<String> getErrors() {
        return errorList;
    }
}
