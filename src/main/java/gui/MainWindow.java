package gui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import myne.Myne;
import myne.command.Command;
import myne.command.CommandParser;
import myne.command.Response;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Myne myne;
    private Stage stage;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
    private final Image myneImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Myne.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Myne instance */
    public void setMyne(Myne myne) {
        this.myne = myne;

        // Add greeting message.
        dialogContainer.getChildren().addAll(
                DialogBox.getMyneDialog(myne.getUi().getGreetingText(), myneImage)
        );
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Myne's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        try {
            Command command = CommandParser.parse(input, myne);
            Response response = command.execute();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMyneDialog(response.getMessage(), myneImage)
            );

            if (!myne.isAlive()) {
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> stage.close());
                pause.play();
            }
        } catch (RuntimeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMyneDialog(e.getMessage(), myneImage)
            );
        }

        userInput.clear();
    }
}
