package myne.task;

import org.junit.jupiter.api.Test;

import myne.TaskStorage;

public class TaskParserTest {
    @Test
    public void fetchTasksTest() {
        TaskParser.parseTaskFile(new TaskStorage("./data/myne.txt").fetchTaskFile());
    }
}
