import java.io.File;
import java.util.ArrayList;

public class TaskParser {
    private File taskFile;

    public TaskParser() {
        this.taskFile = TaskFile.fetchTasks();
    }

    public ArrayList<Task> parseTaskFile() {
        return null;
    }
}
