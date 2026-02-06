package myne;

import myne.task.TaskParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class to handle file reading and saving for Myne's tasks.
 */
public class TaskStorage {
    private final String filePath;

    /**
     * Initializes a <code>TaskStorage</code> with the specified <code>filePath</code>.
     * Tasks are stored in text (.txt) files.
     * @param filePath The file path.
     */
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

    /**
     * Saves the provided tasks into the file path.
     * @param taskList The list of tasks.
     */
    public void saveTasks(TaskList taskList) {
        String serializedTasks = TaskParser.serializeTasks(taskList);
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(serializedTasks);
        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
        }
    }

    /**
     * Creates the task file and the specified parent directories if they do not exist yet.
     * @return The file created by this operation.
     */
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
