
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import db.DbContract;
import db.PostgresHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/java/sample.fxml").toURL();
        Parent root = FXMLLoader.load(url);

        primaryStage.setTitle("Weather & Forecast");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}

//src/main/java/sample.fxml