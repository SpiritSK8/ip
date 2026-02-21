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
import myne.command.Status;

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

        // Taken from ChatGPT: How to disable button when input text is empty.
        // https://chatgpt.com/share/6998b63e-85a8-800d-a1e3-7d7031147a98
        sendButton.disableProperty().bind(
                userInput.textProperty().isEmpty()
        );
    }

    /** Injects the Myne instance */
    public void setMyne(Myne myne) {
        this.myne = myne;

        // Show greeting message.
        addMyneDialog(myne.getUi().getGreetingText());

        Response response = myne.parseTaskFile();
        if (response.getStatus() == Status.FAIL) {
            addMyneDialog(response.getMessage());
        }
    }

    public void setStage(Stage stage) {
        stage.setMinWidth(400.0);
        stage.setMinHeight(400.0);
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Myne's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isBlank()) {
            return;
        }

        try {
            Command command = CommandParser.parse(input, myne);
            Response response = command.execute();
            addUserDialog(input);
            addMyneDialog(response.getMessage());

            if (!myne.isAlive()) {
                exitAppAfterDelay(3.0);
            }
        } catch (RuntimeException e) {
            addUserDialog(input);
            addMyneDialog(e.getMessage());
        }

        userInput.clear();
    }

    private void addUserDialog(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(message, userImage)
        );
    }

    private void addMyneDialog(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getMyneDialog(message, myneImage)
        );
    }

    private void exitAppAfterDelay(double seconds) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> stage.close());
        pause.play();
    }
}
