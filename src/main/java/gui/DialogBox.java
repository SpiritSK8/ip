package gui;

import java.io.IOException;
import java.util.Collections;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * ChatGPT was used to improve the style of DialogBox to match the chatbot personality,
 * helping to find the perfect colors and fonts to use
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
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

        // Add spacing between image and dialog text
        this.setSpacing(8);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user with clean, professional text.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #E8E8E8; -fx-padding: 10; -fx-background-radius: 10;");
        db.dialog.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 15; -fx-text-fill: #000000;");
        db.dialog.setWrapText(true);
        db.dialog.setMaxWidth(250);
        return db;
    }

    /**
     * Creates a fully Emufied dialog box for Emu.
     * Adds playful quirks, pastel styling, text animations, and flips the dialog box.
     */
    public static DialogBox getEmuDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();

        // Text styling for playful Emu vibe
        db.dialog.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14; -fx-text-fill: #ff69b4;");
        db.dialog.setWrapText(true);
        db.dialog.setMaxWidth(250);

        // Background styling
        db.setStyle("-fx-background-color: #FFF0F5; -fx-padding: 10; -fx-background-radius: 15;");

        // Subtle slide-in animation to feel lively and ditzy
        TranslateTransition tt = new TranslateTransition(Duration.millis(300), db);
        tt.setFromX(-20);
        tt.setToX(0);
        tt.play();

        return db;
    }
}