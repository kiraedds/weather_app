import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChartController {

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