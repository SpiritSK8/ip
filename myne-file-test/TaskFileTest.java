import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class TaskFileTest {
    @Test
    public void fetchTasksTest() {
        File taskFile = TaskFile.fetchTaskFile();
        assertEquals(new File("./data/myne.txt"), taskFile);
    }
}
