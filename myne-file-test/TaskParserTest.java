import myne.task.TaskParser;
import myne.TaskStorage;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TaskParserTest {
    @Test
    public void fetchTasksTest() {
        TaskParser.parseTaskFile(new TaskStorage("./data/myne.txt").fetchTaskFile());
    }
}
