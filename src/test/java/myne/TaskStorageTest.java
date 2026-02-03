package myne;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskStorageTest {
    @Test
    public void fetchTaskFileTest() {
        File taskFile = new TaskStorage("./data/myne.txt").fetchTaskFile();
        assertEquals(new File("./data/myne.txt"), taskFile);
    }
}
