package gui;

import java.io.IOException;

import emu.Emu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Emu emu = new Emu();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setTitle("Emu");

            stage.getIcons().add(new javafx.scene.image.Image(
                    Main.class.getResourceAsStream("/images/DaEmu.png")
            ));

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().initialise(emu);
            // inject the Emu instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}