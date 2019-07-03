import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Controller {

    @FXML
    private TextField cityName;

    @FXML
    private TextField countryName;

    @FXML
    private Button getWeather;

    @FXML
    private Button chart;

    @FXML
    private Button history;

    @FXML
    private Button getForecast;

    @FXML
    private TextField text6;

    @FXML
    private TextField text7;

    @FXML
    private TextField text8;

    @FXML
    private TextField text9;

    @FXML
    private TextField text10;

    @FXML
    private TextField text11;

    @FXML
    private TextField text12;

    @FXML
    private TextField text13;

    @FXML
    private TextField text14;

    @FXML
    private TextField text15;

    @FXML
    private TextField text16;

    @FXML
    private TextField text17;

    @FXML
    private TextField text18;

    @FXML
    private TextField text19;

    @FXML
    private TextField text20;

    @FXML
    private TextField text21;

    @FXML
    private TextField text22;

    @FXML
    private TextField text23;

    @FXML
    private TextField text24;

    @FXML
    private TextField text25;

    @FXML
    private TextField text26;

    @FXML
    private TextField text27;

    @FXML
    private TextField text28;

    @FXML
    private TextField text29;

    @FXML
    private TextField text30;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;

    @FXML
    private ImageView icon4;

    @FXML
    private ImageView icon5;

    @FXML
    private ImageView icon6;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private TextField description;

    @FXML
    void GetFiveDaysForecast(ActionEvent event) {

        description.setText("Five Day Weather Forecast");

        String city=cityName.getText();
        String country=countryName.getText();
        label1.setText("");
        label2.setText("");
        GetWeather getWeather= new GetWeather();
        if (checkConditions(city,country)) {
            try {
                ArrayList<String> ar = getWeather.getForecast(city, country);
                text6.setText(ar.get(0));
                text7.setText(ar.get(1));
                text8.setText(ar.get(2));
                text9.setText(ar.get(3));
                text10.setText(ar.get(4) + "m/s  " + ar.get(5));
                icon2.setImage(getWeather.getImage(ar.get(6).substring(1, 4)));
                text11.setText(ar.get(7));
                text12.setText(ar.get(8));
                text13.setText(ar.get(9));
                text14.setText(ar.get(10));
                text15.setText(ar.get(11) + "m/s  " + ar.get(12));
                icon3.setImage(getWeather.getImage(ar.get(13).substring(1, 4)));
                text16.setText(ar.get(14));
                text17.setText(ar.get(15));
                text18.setText(ar.get(16));
                text19.setText(ar.get(17));
                text20.setText(ar.get(18) + "m/s  " + ar.get(19));
                icon4.setImage(getWeather.getImage(ar.get(20).substring(1, 4)));
                text21.setText(ar.get(21));
                text22.setText(ar.get(22));
                text23.setText(ar.get(23));
                text24.setText(ar.get(24));
                text25.setText(ar.get(25) + "m/s  " + ar.get(26));
                icon5.setImage(getWeather.getImage(ar.get(27).substring(1, 4)));
                text26.setText(ar.get(28));
                text27.setText(ar.get(29));
                text28.setText(ar.get(30));
                text29.setText(ar.get(31));
                text30.setText(ar.get(32)+ "m/s  " + ar.get(33));
                icon6.setImage(getWeather.getImage(ar.get(34).substring(1, 4)));
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,
                        "You entered incorrect city or country",
                        "ERROR",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @FXML
    void getDayWeather(ActionEvent event) {

        description.setText("One Day Weather");

        String city=cityName.getText();
        String country=countryName.getText();
        GetWeather getWeather= new GetWeather();
        label1.setText("");
        label2.setText("");
        if (checkConditions(city,country)) {
            try {
                String[] date = getWeather.getCurrentWeather(city, country);
            text6.setText(date[0]);
            text7.setText(date[1]);
            text8.setText(date[2]);
            text9.setText(date[3]);
            text10.setText(date[4] + "m/s  " + date[5]);
            date[6] = date[6].substring(1, 4);
            icon2.setImage(getWeather.getImage(date[6]));
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,
                        "You entered incorrect city or country",
                        "ERROR",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @FXML
    void showChart(ActionEvent event) throws Exception {
        URL url = new File("src/main/java/ChartFXML.fxml").toURL();
        Parent chart_parent = FXMLLoader.load(url);

        Scene chart_scene = new Scene(chart_parent);
        Stage chart_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        chart_stage.setScene(chart_scene);
        chart_stage.show();
    }

    @FXML                                                                   ////////////// DO zrobienia
    void getHistory(ActionEvent event) {
        description.setText("Five Day Weather History");

    }


    boolean checkConditions (String city,String country)
    {
        if (city.equals("") && country.equals("")) {
            label1.setText("City cant be empty");
            label2.setText("Country cant be empty");
            return false;
        }
        else if(city.equals(""))
        {
            label1.setText("City cant be empty");
            return false;
        }
        else if(country.equals(""))
        {
            label2.setText("Country cant be empty");
            return false;
        }
        return true;
    }
}