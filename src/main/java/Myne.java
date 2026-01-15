public class Myne {
    public static void main(String[] args) {
        greet();
    }

    private static void greet() {
        String greetingStart = "Good day to you. My name is";
        String logo = "___  ___\n"
                + "|  \\/  |                 \n"
                + "| .  . |_   _ _ __   ___\n"
                + "| |\\/| | | | | '_ \\ / _ \\\n"
                + "| |  | | |_| | | | |  __/\n"
                + "\\_|  |_/\\__, |_| |_|\\___|\n"
                + "         __/ |\n"
                + "        |___/";
        String greetingEnd = "May our meeting, ordained by the gods be blessed on this fruitful day.";

        String exitMessage = "Farewell. May the time come when our threads of fate are woven together again.";

        System.out.println("_____________________________________________");
        System.out.println(greetingStart + "\n" + logo + "\n" + greetingEnd);
        System.out.println("_____________________________________________");
        System.out.println(exitMessage);
        System.out.println("_____________________________________________");
    }
}
