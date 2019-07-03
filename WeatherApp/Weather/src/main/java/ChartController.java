import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ChartController{


    @FXML
    private ImageView chartView;

    @FXML
    private Button returnButton;

    @FXML
    private Button chartButton;

    @FXML
    void getBack(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/sample.fxml").toURL();
        Parent chart_parent = FXMLLoader.load(url);

        Scene chart_scene = new Scene(chart_parent);
        Stage chart_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        chart_stage.setScene(chart_scene);
        chart_stage.show();

    }

    @FXML
    void showChartView(ActionEvent event) {             /// Tu bedzie pokazywał sie wykres

    }

}