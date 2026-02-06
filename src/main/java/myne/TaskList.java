package myne;

import myne.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        list.add(task);
    }

    public void mark(int taskIndex) throws IndexOutOfBoundsException {
        Task task = list.get(taskIndex);
        task.mark();
    }

    public void unmark(int taskIndex) throws IndexOutOfBoundsException {
        Task task = list.get(taskIndex);
        task.unmark();
    }

    public Task delete(int taskIndex) throws IndexOutOfBoundsException {
        return list.remove(taskIndex);
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public TaskList find(String textToFind) {
        TaskList tasksFound = new TaskList();

        for (Task task : list) {
            if (task.getName().matches(".*" + textToFind + ".*")) { // Matches the text with any prefix/suffix.
                tasksFound.add(task);
            }
        }

        return tasksFound;
    }
}
