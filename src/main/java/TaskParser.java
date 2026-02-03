import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskParser {
    public static String SEPARATOR = "~";

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

    private static Task parseTaskString(String serializedTask) {
        String[] parts = serializedTask.split(SEPARATOR);

        // Determine the task type.
        Task task = switch (parts[0]) {
            case "D" -> new Deadline(parts[2], parts[3]);
            case "E" -> new Event(parts[2], parts[3], parts[4]);
            default -> new Todo(parts[2]);
        };

        // Determine whether the task has been marked or not.
        if (parts[1].equals("1")) {
            task.mark();
        }

        return task;
    }

    public static String serializeTasks(TaskList taskList) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(task.serialize());
            sb.append("\n");
        }

        return sb.toString();
    }
}
