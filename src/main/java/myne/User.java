package myne;

import javafx.scene.paint.Color;

/**
 * A class to encapsulate a username and its color.
 */
public class User {
    public static final User MYNE = new User("Myne", Color.rgb(110, 84, 5));
    public static final User FERDINAND = new User("Ferdinand", Color.rgb(2, 61, 129));

    private final String name;
    private final Color color;

    public User(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
