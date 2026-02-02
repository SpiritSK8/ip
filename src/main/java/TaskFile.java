import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaskFile {
    /**
     * Fetches ./data/myne.txt file that contains the saved tasks. This method
     * will create the data directory and myne.txt file if they do not exist.
     *
     * @return A <code>File</code> wrapper for ./data/myne.txt.
     */
    public static File fetchTaskFile() {
        createDataDirectory();
        return createTaskFile();
    }

    public static void saveTasks(List<Task> taskList) {
        String serializedTasks = TaskParser.serializeTasks(taskList);
        try (FileWriter fileWriter = new FileWriter("./data/myne.txt")) {
            fileWriter.write(serializedTasks);
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    private static void createDataDirectory() {
        File dataDirectory = new File("./data");
        dataDirectory.mkdirs();
    }

    private static File createTaskFile() {
        File taskFile = new File("./data/myne.txt");

        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
        }

        return taskFile;
    }
}
