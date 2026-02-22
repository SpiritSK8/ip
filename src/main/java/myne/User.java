package myne;

import javafx.scene.paint.Color;

/**
 * A class to encapsulate a username and its color.
 */
public record User(String name, Color color) {
    public static final User MYNE = new User("Myne", Color.rgb(173, 132, 7));
    public static final User FERDINAND = new User("Ferdinand", Color.rgb(2, 61, 129));
}
