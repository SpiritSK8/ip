import java.io.File;
import java.io.IOException;

public class TaskFile {
    /**
     * Fetches ./data/myne.txt file that contains the saved tasks. This method
     * will create the data directory and myne.txt file if they do not exist.
     *
     * @return A <code>File</code> wrapper for ./data/myne.txt.
     */
    public static File fetchTasks() {
        createDataDirectory();
        return createTaskFile();
    }

    private static void createDataDirectory() {
        File dataDirectory = new File("./data");
        if (dataDirectory.mkdirs()) {
            System.out.println("File created: " + dataDirectory.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    private static File createTaskFile() {
        File taskFile = new File("./data/myne.txt");

        try {
            if (taskFile.createNewFile()) {
                System.out.println("File created: " + taskFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
        }

        return taskFile;
    }
}
