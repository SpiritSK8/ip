package myne;

import java.util.ArrayList;
import java.util.List;

import myne.command.Response;
import myne.command.Status;
import myne.task.Task;

/**
 * A class that stores the tasks for Myne and supports operations to modify them.
 */
public class TaskList {
    private final List<Task> list;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Initializes a task list from a given <code>List</code> of tasks.
     * @param list The <code>List</code> containing the tasks.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list.
     * @param task The <code>Task</code> to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Marks a task in the list.
     * @param taskIndex The index of the task to be marked.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds.
     */
    public void mark(int taskIndex) throws IndexOutOfBoundsException {
        Task task = list.get(taskIndex);
        task.mark();
    }

    /**
     * Unmarks a task in the list.
     * @param taskIndex The index of the task to be unmarked.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds.
     */
    public void unmark(int taskIndex) throws IndexOutOfBoundsException {
        Task task = list.get(taskIndex);
        task.unmark();
    }

    /**
     * Deletes a task in the list.
     * @param taskIndex The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds.
     */
    public Task delete(int taskIndex) throws IndexOutOfBoundsException {
        return list.remove(taskIndex);
    }

    /**
     * Removes the first occurrence of the specified <code>Task</code> from this list, if it is present (optional operation).
     * If this list does not contain the element, it is unchanged.
     * Returns true if this list contained the specified element.
     * @param task The <code>Task</code> to delete
     * @return <code>true</code> if the list contains the task.
     */
    public boolean delete(Task task) {
        return list.remove(task);
    }

    /**
     * Returns the specified task.
     * @param index The index of the task.
     * @return The <code>Task</code>.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return list.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns <code>true</code> if the list is empty. <code>false</code> otherwise.
     * @return Whether the list is empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Finds all tasks that contains the specified text. Search is case-insensitive.
     * @param textToFind The text to find.
     * @return A new <code>TaskList</code> containing the tasks found.
     */
    public TaskList find(String textToFind) {
        TaskList tasksFound = new TaskList();

        // Matches the text with any prefix/suffix, ignoring case.
        list.stream()
                .filter(task -> task.getName().toLowerCase().contains(textToFind.toLowerCase()))
                .forEach(tasksFound::add);

        return tasksFound;
    }
}
