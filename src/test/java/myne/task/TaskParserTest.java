package myne.task;

import myne.TaskStorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskParserTest {
    @Test
    public void fetchTasksTest() {
        TaskParser.parseTaskFile(new TaskStorage("./data/myne.txt").fetchTaskFile());
    }
}
