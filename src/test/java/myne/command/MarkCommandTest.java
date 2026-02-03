package myne.command;

import myne.Myne;

import myne.task.Task;
import myne.task.Todo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkCommandTest {
    @Test
    public void testMarkCommand() {
        // Reset the test.txt file.
        File taskFile = new File("./data/test.txt");
        try {
            taskFile.getParentFile().mkdirs(); // Creates data directory first.
            taskFile.delete();
            taskFile.createNewFile();
            taskFile.deleteOnExit();
        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
        }

        Myne myne = new Myne("./data/test.txt");

        // Test: Attempt marking a task that doesn't exist.
        MarkCommand command = new MarkCommand(myne, "1");
        assertThrows(IndexOutOfBoundsException.class, command::execute);

        // Add new task manually.
        myne.getTaskList().add(new Todo("Test"));

        command.execute();
        Task actualTask = myne.getTaskList().get(0);

        // Marked task shouldn't equal unmarked task.
        Task expectedTask = new Todo("Test");
        assertNotEquals(expectedTask, actualTask);

        // Test if task was marked successfully.
        expectedTask.mark();
        assertEquals(expectedTask, actualTask);
    }
}
