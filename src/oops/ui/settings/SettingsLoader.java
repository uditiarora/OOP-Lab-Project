package oops.ui.settings;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oops.database.DatabaseHandler;

public class SettingsLoader extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/oops/ui/settings/settings.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Settings");

        new Thread(() -> {
            DatabaseHandler.getInstance();
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
