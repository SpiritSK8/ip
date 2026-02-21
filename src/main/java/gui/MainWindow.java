package gui;

import java.util.HashMap;
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

import myne.FerMyneException;
import myne.FerMyneFace;
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

    private final HashMap<FerMyneFace, Image> ferMyneImages = new HashMap<>();

    @FXML
    public void initialize() {
        setImages();

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
        addMyneDialog(myne.getUi().getGreetingText(), FerMyneFace.MYNE_THANKFUL);

        Response response = myne.parseTaskFile();
        if (response.getStatus() == Status.FAIL) {
            addMyneDialog(response.getMessage(), response.getFace());
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
            addMyneDialog(response.getMessage(), response.getFace());

            if (!myne.isAlive()) {
                exitAppAfterDelay(4.0);
            }
        } catch (FerMyneException e) {
            addUserDialog(input);
            addMyneDialog(e.getMessage(), e.getFace());
        } catch (RuntimeException e) {
            addUserDialog(input);
            addMyneDialog(e.getMessage(), FerMyneFace.MYNE_WORRIED); // Default face for errors.
        }

        userInput.clear();
    }

    private void addUserDialog(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(message, userImage)
        );
    }

    private void addMyneDialog(String message, FerMyneFace face) {
        dialogContainer.getChildren().addAll(
                DialogBox.getMyneDialog(message, ferMyneImages.get(face))
        );
    }

    private void exitAppAfterDelay(double seconds) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> stage.close());
        pause.play();
    }

    private void setImages() {
        ferMyneImages.put(FerMyneFace.MYNE_DEFAULT,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/MyneDefault.png"))));
        ferMyneImages.put(FerMyneFace.MYNE_HAPPY,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/MyneHappy.png"))));
        ferMyneImages.put(FerMyneFace.MYNE_THANKFUL,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/MyneThankful.png"))));
        ferMyneImages.put(FerMyneFace.MYNE_WONDER,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/MyneWonder.png"))));
        ferMyneImages.put(FerMyneFace.MYNE_JOYFUL,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/MyneJoyful.png"))));
        ferMyneImages.put(FerMyneFace.MYNE_CONFUSED,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/MyneConfused.png"))));
        ferMyneImages.put(FerMyneFace.MYNE_WORRIED,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/MyneWorried.png"))));
        ferMyneImages.put(FerMyneFace.MYNE_DISGUSTED,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/MyneDisgusted.png"))));
        ferMyneImages.put(FerMyneFace.FERDINAND_DEFAULT,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/FerdinandDefault.png"))));
        ferMyneImages.put(FerMyneFace.FERDINAND_HAPPY,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/FerdinandHappy.png"))));
        ferMyneImages.put(FerMyneFace.FERDINAND_EXASPERATED,
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/FerdinandExasperated.png"))));
    }
}
