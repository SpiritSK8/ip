package gui;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
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
import myne.MyneException;
import myne.MyneFace;
import myne.User;
import myne.command.Command;
import myne.command.CommandParser;
import myne.command.Response;
import myne.command.Status;
import utility.CommandHistory;

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
    private CommandHistory commandHistory = new CommandHistory();

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));

    private final HashMap<MyneFace, Image> ferMyneImages = new HashMap<>();

    /**
     * Makes the ScrollPane auto-scroll and disables send button when user input is empty.
     */
    @FXML
    public void initialize() {
        setImages();

        // Automatically scroll to the bottom when a chat is sent.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Taken from ChatGPT: How to disable button when input text is empty.
        // https://chatgpt.com/share/6998b63e-85a8-800d-a1e3-7d7031147a98
        sendButton.disableProperty().bind(
                userInput.textProperty().isEmpty()
        );

        // Add up/down arrow listener for command history.
        userInput.setOnKeyPressed(event -> {
            if (myne.isAlive()) {
                return;
            }
            switch (event.getCode()) {
                case UP:
                    showPrevCommand();
                    break;
                case DOWN:
                    showNextCommand();
                    break;
                default:
                    break;
            }
        });
    }

    /** Injects the Myne instance */
    public void setMyne(Myne myne) {
        this.myne = myne;
        showGreeting(this::parseTaskFile);
    }

    public void setStage(Stage stage) {
        stage.setMinWidth(400.0);
        stage.setMinHeight(400.0);
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing the user input and the other containing Myne's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isBlank()) {
            return;
        }

        commandHistory.add(input);

        try {
            Command command = CommandParser.parse(input, myne);
            Response response = command.execute();
            addUserDialog(input);
            addMyneDialog(response);

            if (!myne.isAlive()) {
                exitAppAfterDelay(4.0);
            }
        } catch (MyneException e) {
            addUserDialog(input);
            addMyneDialog(e.getMessage(), e.getFace(), e.getUser());
        } catch (RuntimeException e) {
            addUserDialog(input);
            addMyneDialog(e.getMessage(), MyneFace.MYNE_WORRIED, User.MYNE); // Default face for errors.
        }

        userInput.clear();
    }

    private void addUserDialog(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(message, userImage)
        );
    }

    private void addMyneDialog(Response response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getMyneDialog(
                        response.getMessage(), ferMyneImages.get(response.getFace()), response.getUser())
        );
    }

    private void addMyneDialog(String message, MyneFace face, User user) {
        dialogContainer.getChildren().addAll(
                DialogBox.getMyneDialog(message, ferMyneImages.get(face), user)
        );
    }

    private void exitAppAfterDelay(double seconds) {
        // Disable user input first.
        userInput.setDisable(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(e -> stage.close());
        pause.play();
    }

    private void setImages() {
        ferMyneImages.put(MyneFace.MYNE_DEFAULT,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/MyneDefault.png"))));
        ferMyneImages.put(MyneFace.MYNE_HAPPY,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/MyneHappy.png"))));
        ferMyneImages.put(MyneFace.MYNE_THANKFUL,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/MyneThankful.png"))));
        ferMyneImages.put(MyneFace.MYNE_WONDER,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/MyneWonder.png"))));
        ferMyneImages.put(MyneFace.MYNE_JOYFUL,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/MyneJoyful.png"))));
        ferMyneImages.put(MyneFace.MYNE_CONFUSED,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/MyneConfused.png"))));
        ferMyneImages.put(MyneFace.MYNE_WORRIED,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/MyneWorried.png"))));
        ferMyneImages.put(MyneFace.MYNE_DISGUSTED,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/MyneDisgusted.png"))));
        ferMyneImages.put(MyneFace.FERDINAND_DEFAULT,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/FerdinandDefault.png"))));
        ferMyneImages.put(MyneFace.FERDINAND_HAPPY,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/FerdinandHappy.png"))));
        ferMyneImages.put(MyneFace.FERDINAND_EXASPERATED,
                new Image(Objects.requireNonNull(
                        this.getClass().getResourceAsStream("/images/FerdinandExasperated.png"))));
    }

    private void showGreeting(Runnable then) {
        // Disable user input until greetings have been shown.
        userInput.setDisable(true);

        List<Response> greetings = myne.getUi().getGreetings();
        PauseTransition[] pauses = greetings.stream().map(
                // Maps each response into a PauseTransition with a duration of X second. This essentially
                // builds a sequence of actions to be played sequentially with a delay of X second in between.
                response -> {
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.9));
                    pause.setOnFinished(e -> addMyneDialog(response));
                    return pause;
                }
        ).toArray(PauseTransition[]::new);

        SequentialTransition sequence = new SequentialTransition(pauses);

        sequence.setOnFinished(e -> {
            userInput.setDisable(false);
            then.run();
        });

        sequence.play();
    }

    private void parseTaskFile() {
        Response response = myne.parseTaskFile();
        if (response.getStatus() == Status.FAIL) {
            addMyneDialog(response);
        }
    }

    private void showPrevCommand() {
        String prevCommand = commandHistory.prevCommand(userInput.getText());
        userInput.setText(prevCommand);
        userInput.end(); // Sets the editing position to the end of text.
    }

    private void showNextCommand() {
        String nextCommand = commandHistory.nextCommand(userInput.getText());
        userInput.setText(nextCommand);
        userInput.end(); // Sets the editing position to the end of text.
    }
}
