import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class TaskParserTest {
    @Test
    public void fetchTasksTest() {
        TaskParser.parseTaskFile(TaskFile.fetchTaskFile());
    }
}
