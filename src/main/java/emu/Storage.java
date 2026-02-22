package emu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles persistence of tasks by saving them to
 * and loading them from a file on the hard disk.
 */
public class Storage {
    private static final String DIVIDER = " \\| ";
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String COMPLETED_MARKER = "X";

    private final File storageFile;

    /**
     * Creates a Storage object that uses the given file path
     * to persist task data.
     *
     * @param filePath Path to the storage file.
     * @throws EmuException If the storage file cannot be created.
     */
    public Storage(String filePath) throws EmuException {
        assert filePath != null : "filePath should not be null";
        assert !filePath.isEmpty() : "filePath should not be empty";

        this.storageFile = new File(filePath);
        initialiseFile();
    }

    /**
     * Creates the storage file and its parent directories if they
     * do not exist.
     *
     * @throws EmuException If the file cannot be accessed.
     */
    private void initialiseFile() throws EmuException {
        try {
            File parentDirectory = storageFile.getParentFile();
            if (parentDirectory != null) {
                parentDirectory.mkdir();
            }
            storageFile.createNewFile();
        } catch (IOException e) {
            throw new EmuException("I couldn't access the storage file!");
        }
    }

    /**
     * Loads tasks from the storage file and reconstructs
     * them into a TaskList.
     *
     * @return A TaskList containing all stored tasks.
     * @throws EmuException If the storage file cannot be read.
     */
    public TaskList initialiseList() throws EmuException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(storageFile)) {
            while (scanner.hasNextLine()) {
                Task task = readStorageTask(scanner.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new EmuException("UWA!!! I can't seem to find your past tasks!");
        }

        return new TaskList(tasks);
    }

    /**
     * Writes the current state of the given tasks
     * to the storage file, overwriting any existing data.
     *
     * @param tasks The up-to-date task list to be stored.
     * @throws EmuException If the tasks cannot be written to the file.
     */
    public void resetFile(TaskList tasks) throws EmuException {
        assert tasks != null : "tasks cannot be null";

        initialiseFile();

        try (FileWriter writer = new FileWriter(storageFile)) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                assert task != null : "task should not be null";
                writer.write(task.toStorageString() + "\n");
            }
        } catch (IOException e) {
            throw new EmuException("I couldn't record the tasks!");
        }
    }

    /**
     * Reads a single line from the storage file and converts it
     * into the corresponding Task object.
     *
     * @param line A single line from the storage file.
     * @return The reconstructed Task, or null if the line is invalid.
     */
    public static Task readStorageTask(String line) {
        assert line != null : "line should not be null";
        assert !line.isEmpty() : "line should not be empty";

        String[] parts = line.split(DIVIDER);
        Task task;

        switch (parts[0]) {
        case TODO_TYPE -> task = new ToDo(parts[2]);
        case DEADLINE_TYPE -> task = new Deadline(parts[2], parts[3]);
        case EVENT_TYPE -> task = new Event(parts[2], parts[3], parts[4]);
        default -> {
            return null;
        }
        }

        if (COMPLETED_MARKER.equals(parts[1])) {
            task.markComplete();
        }

        return task;
    }
}