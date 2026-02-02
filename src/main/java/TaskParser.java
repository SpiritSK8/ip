import java.io.File;
import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskParser {
    public static String SEPARATOR = "//";

    public static ArrayList<Task> parseTaskFile(File taskFile) {
        ArrayList<Task> taskList = new ArrayList<>();

        try (Scanner sc = new Scanner(taskFile)) {
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                Task task = parseTaskString(data);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Task file not found.");
        }

        return taskList;
    }

    private static Task parseTaskString(String serializedTask) {
        String[] parts = serializedTask.split(SEPARATOR);
        Arrays.stream(parts).forEach(System.out::println);

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

    public static String serializeTasks(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();

        for (Task task : taskList) {
            sb.append(task.serialize());
            sb.append("\n");
        }

        return sb.toString();
    }
}
