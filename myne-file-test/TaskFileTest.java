import myne.TaskStorage;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TaskFileTest {
    @Test
    public void fetchTaskFileTest() {
        File taskFile = new TaskStorage("./data/myne.txt").fetchTaskFile();
        assertEquals(new File("./data/myne.txt"), taskFile);
    }
}
