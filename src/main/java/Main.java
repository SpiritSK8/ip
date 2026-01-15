import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Myne myne = new Myne();
        myne.greet();

        Scanner sc = new Scanner(System.in);
        boolean alive = true;
        while (alive) {
            String input = sc.nextLine();
            System.out.println("_____________________________________________");
            switch (input) {
                case "bye":
                    myne.exit();
                    System.out.println("_____________________________________________");
                    alive = false;
                    break;
                case "list":
                    myne.listItems();
                    System.out.println("_____________________________________________");
                    break;
                default:
                    myne.addTask(input);
                    System.out.println("_____________________________________________");
                    break;
            }
        }
    }
}
