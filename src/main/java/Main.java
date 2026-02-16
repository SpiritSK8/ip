import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
