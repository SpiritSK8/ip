import myne.Myne;

/**
 * Entrance point of the program.
 */
public class Main {
    public static void main(String[] args) {
        Myne myne = new Myne("./data/myne.txt");
        myne.run();
    }
}
