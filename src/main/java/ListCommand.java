public class ListCommand implements Command {
    private final MyneUi ui;
    private final TaskList taskList;

    public ListCommand(Myne myne) {
        this.ui = myne.getUi();
        this.taskList = myne.getTaskList();
    }

    @Override
    public void execute() {
        if (taskList.isEmpty()) {
            ui.showDivider();
            System.out.println("Hm... It appears you are under-worked. Shall we remedy that?");
            ui.showDivider();
        }

        ui.showDivider();
        System.out.println("Behold, your tasks!");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("  " + (i + 1) + "." + taskList.get(i));
        }
        ui.showDivider();
    }
}
