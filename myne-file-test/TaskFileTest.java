import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TaskFileTest {
    @Test
    public void fetchTaskFileTest() {
        File taskFile = TaskFile.fetchTaskFile();
        assertEquals(new File("./data/myne.txt"), taskFile);
    }
}
