package gui;

import emu.Emu;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Emu emu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image emuImage = new Image(this.getClass().getResourceAsStream("/images/DaEmu.png"));

    /**
     * Initialises the Emu instance and gets the greeting message,
     * then puts the message on the dialog
     *
     * @param emu The Chatbot to be initialised
     */
    @FXML
    public void initialise(Emu emu) {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // initialises Emu and gets startup message
        this.emu = emu;
        String greeting = emu.initialiseTaskList();
        assert greeting != null : "greeting should not be null";
        assert !greeting.isEmpty() : "greeting should not be empty";

        dialogContainer.getChildren().addAll(
                DialogBox.getEmuDialog(greeting, emuImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Emu's
     * reply and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = emu.respond(input);

        assert input != null : "input should not be null";

        assert response != null : "response should not be null";
        assert !response.isEmpty() : "response should not be empty";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEmuDialog(response, emuImage)
        );

        // Closes the system if Emu exit status is true.
        if (emu.getExitStatus()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.0));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }

        userInput.clear();
    }
}