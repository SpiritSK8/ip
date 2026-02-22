package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import emu.Deadline;
import emu.EmuException;
import emu.Event;
import emu.Storage;
import emu.Task;
import emu.TaskList;
import emu.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void testInitialiseList() {
        // 3 different types of test tasks.
        String testTodoMark = "T | X | task\n";
        String testDeadlineUnmark = "D |   | time | something\n";
        String testEventMark = "E |   | now | some | thing\n";

        try {
            // Create temporary file.
            File tempFile = File.createTempFile("test-list", ".txt");
            tempFile.deleteOnExit();

            // Write lines to temporary file.
            FileWriter writer = new FileWriter(tempFile);
            writer.write(testTodoMark +
                    testDeadlineUnmark +
                    testEventMark);
            writer.close();

            // Pass the temp file to Storage.
            Storage storage = new Storage(tempFile.getAbsolutePath());
            TaskList tasks = storage.initialiseList();

            // Correct tasks.
            ArrayList<Task> correctTasks = new ArrayList<>();
            ToDo toDoMark = new ToDo("task");
            toDoMark.markComplete();
            Deadline deadlineUnmark = new Deadline("time", "something");
            Event eventUnmark = new Event("now", "some", "thing");

            // Correct TaskList.
            correctTasks.add(toDoMark);
            correctTasks.add(deadlineUnmark);
            correctTasks.add(eventUnmark);

            TaskList sample = new TaskList(correctTasks);

            assertEquals(sample.listTasks(), tasks.listTasks());
        } catch (IOException | EmuException e) {
            fail(); // The test should not reach this line.
        }
    }

    @Test
    public void testResetFile() {
        // 3 different types of test tasks.
        ToDo toDoMark = new ToDo("task");
        toDoMark.markComplete();
        Deadline deadlineUnmark = new Deadline("time", "something");
        Event eventUnmark = new Event("now", "some", "thing");

        try {
            // Create TaskList.
            ArrayList<Task> correctTasks = new ArrayList<>();
            correctTasks.add(toDoMark);
            correctTasks.add(deadlineUnmark);
            correctTasks.add(eventUnmark);
            TaskList sample = new TaskList(correctTasks);

            // Create temp file for Storage.
            File tempFile = File.createTempFile("test-list", ".txt");
            tempFile.deleteOnExit();

            // Try {@code resetFile} and get file.
            Storage storage = new Storage(tempFile.getAbsolutePath());
            storage.resetFile(sample);

            // Get text out of file.
            String text = "";
            Scanner scanner = new Scanner(tempFile);
            while (scanner.hasNextLine()) {
                text = text + scanner.nextLine() + "\n";
            }

            // Correct string.
            String expected = "T | X | task\n" +
                    "D |   | time | something\n" +
                    "E |   | now | some | thing\n";

            assertEquals(expected, text);
        } catch (IOException | EmuException e) {
            fail(); // The test should not reach this line
        }
    }
}