package myne.task;

import myne.TaskList;

import java.io.File;
import java.io.FileNotFoundException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A utility class to read tasks from a file and encode tasks as plaintext.
 */
public class TaskParser {
    /**
     * The separator/delimiter for saving tasks as plaintext. Task data cannot contain this character.
     */
    public final static String SEPARATOR = "~";

    /**
     * Reads the given file and parses the tasks inside. Tasks with incorrect format are skipped.
     * @param taskFile The file containing the task data.
     * @return An <code>ArrayList&lt;Task&gt;</code> containing the tasks read from the file, or empty if the file is not found.
     */
    public static ArrayList<Task> parseTaskFile(File taskFile) {
        ArrayList<Task> taskList = new ArrayList<>();

        int i = 1;
        try (Scanner sc = new Scanner(taskFile)) {
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                try {
                    Task task = parseTaskString(data);
                    taskList.add(task);
                } catch (RuntimeException e) {
                    System.out.println("Error loading task " + i);
                    System.out.println(e.getMessage());
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Task file not found.");
        }

        return taskList;
    }

    /**
     * Returns the string representation of all the tasks in the <code>taskList</code>.
     * This text can be immediately saved into a file and used by Myne.
     * @param taskList The task list.
     * @return The string representation of all the tasks in the <code>taskList</code>.
     */
    public static String serializeTasks(TaskList taskList) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(task.serialize());
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Parses individual tasks.
     * @param serializedTask The plaintext representation of the task.
     * @return An instance of a subclass of <code>Task</code>.
     */
    private static Task parseTaskString(String serializedTask) {
        String[] parts = serializedTask.split(SEPARATOR);

        // Determine the task type.
        Task task = switch (parts[0]) {
            case "D" -> new Deadline(parts[2], LocalDate.parse(parts[3]));
            case "E" -> new Event(parts[2], LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
            default -> new Todo(parts[2]);
        };

        // Determine whether the task has been marked or not.
        if (parts[1].equals("1")) {
            task.mark();
        }

        return task;
    }
}
