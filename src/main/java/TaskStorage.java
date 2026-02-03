import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaskStorage {
    private String filePath;

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Fetches ./data/myne.txt file that contains the saved tasks. This method
     * will create the data directory and myne.txt file if they do not exist.
     *
     * @return A <code>File</code> wrapper for ./data/myne.txt.
     */
    public File fetchTaskFile() {
        File taskFile = new File(filePath);
        if (taskFile.exists()) {
            return taskFile;
        } else {
            return createTaskFile();
        }
    }

    public void saveTasks(TaskList taskList) {
        String serializedTasks = TaskParser.serializeTasks(taskList);
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(serializedTasks);
        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
        }
    }

    private File createTaskFile() {
        File taskFile = new File(filePath);

        try {
            taskFile.getParentFile().mkdirs(); // Creates data directory first.
            taskFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
        }

        return taskFile;
    }
}
