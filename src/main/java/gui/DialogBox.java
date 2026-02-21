package gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    /**
     * Chat bubble margin for the opposite side.
     * For example, the user's chat bubbles are on the left, so extra margin on the right is needed.
     */
    private static final double oppositeMargin = 88.0;

    @FXML
    private VBox dialogBubble;
    @FXML
    private Label dialog;
    @FXML
    private Polygon bubbleTail;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    @FXML
    public void initialize() {
        // Clips the profile image into a circle.
        double radius = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2;
        Circle clip = new Circle(radius, radius, radius);
        displayPicture.setClip(clip);

        // Sets the margin.
        setPadding(new Insets(5.0, 5.0, 5.0, oppositeMargin));
    }

    /**
     * Flips the dialog box for messages sent by the user.
     */
    private void flip() {
        // Flips the order of elements (i.e. from (label, image) to (image, label)).
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);

        // Flips the tail.
        bubbleTail.setScaleX(-1.0);
        bubbleTail.setTranslateX(1.0);

        setAlignment(Pos.TOP_LEFT);

        dialogBubble.getStyleClass().add("reply-dialog-bubble");

        // Taken from ChatGPT: https://chatgpt.com/share/6998b63e-85a8-800d-a1e3-7d7031147a98
        setPadding(new Insets(5.0, oppositeMargin, 5.0, 5.0)); // More right margin
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    public static DialogBox getMyneDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
}
