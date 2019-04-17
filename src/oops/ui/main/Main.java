package oops.ui.main;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oops.database.DatabaseHandler;
import oops.exceptions.ExceptionUtil;
import oops.util.LibraryAssistantUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {

    private final static Logger LOGGER = LogManager.getLogger(Main.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/oops/ui/login/login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Login");

        LibraryAssistantUtil.setStageIcon(stage);

        new Thread(() -> {
            ExceptionUtil.init();
            DatabaseHandler.getInstance();
        }).start();
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "App launched on {}", LibraryAssistantUtil.formatDateTimeString(startTime));
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Long exitTime = System.currentTimeMillis();
                LOGGER.log(Level.INFO, "App is closing on {}. Used for {} ms", LibraryAssistantUtil.formatDateTimeString(startTime), exitTime);
            }
        });
    }

}
