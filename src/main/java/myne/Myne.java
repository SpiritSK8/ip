package myne;

import myne.command.Response;
import myne.command.Status;
import myne.task.TaskParseResult;
import myne.task.TaskParser;

/**
 * <code>___..___.................</code><br>
 * <code>|..\/..|.................</code><br>
 * <code>| .... |_..._ _ __...___.</code><br>
 * <code>| |\/| | | | | '_ \ / _ \</code><br>
 * <code>| |..| | |_| | | | | .__/</code><br>
 * <code>\_|..|_/\__, |_| |_|\___|</code><br>
 * <code>.........__/ |...........</code><br>
 * <code>........|___/............</code>
 */
public class Myne {
    private final MyneUi ui;
    private final TaskStorage storage;
    private final TaskList taskList;
    private boolean isAlive;

    /**
     * Creates an instance of Myne that stores tasks in the specified <code>filePath</code>.
     * Myne stores tasks in a text (.txt) file.
     * Bring Myne to life by calling <code>run()</code>.
     * @param filePath The file path to the file storage.
     */
    public Myne(String filePath) {
        this.ui = new MyneUi();
        this.storage = new TaskStorage(filePath);
        this.taskList = new TaskList();
        this.isAlive = true;
    }

    /**
     * Parses the task file and returns a response.
     * @return A response
     */
    public Response parseTaskFile() {
        TaskParseResult parseResult = TaskParser.parseTaskFile(storage.fetchTaskFile());

        parseResult.getTasks().forEach(taskList::add);

        if (parseResult.hasError()) {
            String errorHeader = "Hmm, I don't recognize this language... Can you decipher it for me?\n\n";
            String errorMessage = errorHeader + String.join("\n", parseResult.getErrors());
            return new Response(errorMessage, Status.FAIL, FerMyneFace.MYNE_WORRIED);
        }

        return new Response("Parse task success.", Status.SUCCESS, FerMyneFace.MYNE_DEFAULT);
    }

    /**
     * Stops Myne.
     */
    public void exit() {
        this.isAlive = false;
    }

    public MyneUi getUi() {
        return this.ui;
    }

    public TaskStorage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public boolean isAlive() {
        return this.isAlive;
    }
}
