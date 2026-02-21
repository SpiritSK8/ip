import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import gui.MainWindow;
import myne.Myne;

/**
 * A GUI for Myne using FXML.
 */
public class Main extends Application {

    private Myne myne = new Myne("./data/myne.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Set icon. From https://stackoverflow.com/questions/10121991/javafx-application-icon
            stage.setTitle("Myne");
            stage.getIcons().add(new Image(Objects.requireNonNull(
                    Main.class.getResourceAsStream("/images/Background.png"))));

            // Inject dependencies.
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setMyne(myne);
            mainWindow.setStage(stage);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
