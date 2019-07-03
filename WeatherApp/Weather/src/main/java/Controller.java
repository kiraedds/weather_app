import db.DbContract;
import db.PostgresHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Controller {
    int hello=0; //history? //if 0 then no
    @FXML
    private TextField cityName;

    @FXML
    private TextField countryName;

    @FXML
    private Button getWeather;

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
    private MenuItem temperatureCh;

    @FXML
    private MenuItem pressureCh;

    @FXML
    private MenuItem humidityCh;

    @FXML
    private MenuItem WindCh;

    @FXML
    void GetFiveDaysForecast(ActionEvent event) {
        hello=0;
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
                text10.setText(ar.get(5) + "m/s");
                icon2.setImage(getWeather.getImage(ar.get(6).substring(1, 4)));
                text11.setText(ar.get(7));
                text12.setText(ar.get(8));
                text13.setText(ar.get(9));
                text14.setText(ar.get(10));
                text15.setText(ar.get(12) + "m/s");
                icon3.setImage(getWeather.getImage(ar.get(13).substring(1, 4)));
                text16.setText(ar.get(14));
                text17.setText(ar.get(15));
                text18.setText(ar.get(16));
                text19.setText(ar.get(17));
                text20.setText(ar.get(19) + "m/s");
                icon4.setImage(getWeather.getImage(ar.get(20).substring(1, 4)));
                text21.setText(ar.get(21));
                text22.setText(ar.get(22));
                text23.setText(ar.get(23));
                text24.setText(ar.get(24));
                text25.setText(ar.get(26) + "m/s");
                icon5.setImage(getWeather.getImage(ar.get(27).substring(1, 4)));
                text26.setText(ar.get(28));
                text27.setText(ar.get(29));
                text28.setText(ar.get(30));
                text29.setText(ar.get(31));
                text30.setText(ar.get(33)+ "m/s");
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
        hello=0;
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
                text10.setText(date[5] + "m/s");
                date[6] = date[6].substring(1, 4);
                icon2.setImage(getWeather.getImage(date[6]));

                //=============================
                //db

                PostgresHelper client = new PostgresHelper(
                        DbContract.HOST,
                        DbContract.DB_NAME,
                        DbContract.USERNAME,
                        DbContract.PASSWORD);

                try {
                    if (client.connect()) {
                        System.out.println("DB connected");
                    }

                } catch ( SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                String time = dateFormat.format(calendar.getTime()).toString();
                String place = city +" "+country;

                Map vals = client.prepare(place,
                        time,
                        date[0],
                        date[3],
                        date[2],
                        date[1],
                        date[4],
                        date[5]
                );
                try{
                if (client.insert("weatherinfo", vals) == 1) {
                    System.out.println("Record added");
                }
                else
                {
                    System.out.println("NOT ADDED");
                }}catch(SQLException e){
                    e.printStackTrace();
                }

                /*
                String[] hey = client.select("08/06/2019",city +" "+country);
                System.out.println(hey.length);
                for(int i=0; i<hey.length;i++) {
                    System.out.println(hey[i]);
                }*/

                /**/
                //=============================
                System.out.println("kasowanie");
                text11.setText("");
                text12.setText("");
                text13.setText("");
                text14.setText("");
                text15.setText("");
                icon3.setImage(null);
                text16.setText("");
                text17.setText("");
                text18.setText("");
                text19.setText("");
                text20.setText("");
                icon4.setImage(null);
                text21.setText("");
                text22.setText("");
                text23.setText("");
                text24.setText("");
                text25.setText("");
                icon5.setImage(null);
                text26.setText("");
                text27.setText("");
                text28.setText("");
                text29.setText("");
                text30.setText("");
                icon6.setImage(null);
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

    private void fill(TextField a,
                      TextField b,
                      TextField c,
                      TextField d,
                      TextField e,
                      ImageView image,
                      String[] s){

        a.setText(s[2]);
        image.setImage(new Image("http://openweathermap.org/img/w/" + s[2]+ ".png"));
        //System.out.println(s[2].substring(1,4));
        //System.out.println(image.getImage().toString());
        b.setText(s[3]);
        c.setText(s[5]);
        d.setText(s[4]);
        e.setText(s[7]);


    }
    private String getMeXDayBackDate(int x){
        //return new Date(System.currentTimeMillis()- x * 24*60*60*1000);
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //System.out.println("Today's date is "+dateFormat.format(cal.getTime()));

        cal.add(Calendar.DATE, -x);
        //System.out.println("X's before  date was "+dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
    }
    @FXML                                                                   ////////////// DO zrobienia
    void getHistory(ActionEvent event) throws SQLException {
        hello=1;
        description.setText("Five Day Weather History");

        String city=cityName.getText();
        String country=countryName.getText();

        PostgresHelper client = new PostgresHelper(
                DbContract.HOST,
                DbContract.DB_NAME,
                DbContract.USERNAME,
                DbContract.PASSWORD);

        try {
            if (client.connect()) {
                System.out.println("DB connected");
            }

        } catch ( SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String[] hey = new String[8];
        for(int i=0;i<5;i++){
            hey = client.select(getMeXDayBackDate(i).toString(),city+" "+country);
            for(int j=0; j<hey.length;j++) {
                //System.out.println(hey[j]);
            }

            if(i==0) {
                fill(text26,
                        text27,
                        text28,
                        text29,
                        text30,
                        icon6,
                        hey);
            }
            if(i==1){
                fill(text21,
                        text22,
                        text23,
                        text24,
                        text25,
                        icon5,
                        hey);
            }
            if(i==2){
                fill(text16,
                        text17,
                        text18,
                        text19,
                        text20,
                        icon4,
                        hey);
            }
            if(i==3){
                fill(text11,
                        text12,
                        text13,
                        text14,
                        text15,
                        icon3,
                        hey);
            }
            if(i==4){
                fill(text6,
                        text7,
                        text8,
                        text9,
                        text10,
                        icon2,
                        hey);
            }
        }
        /*
        String[] hey = client.select("07/06/2019");
        System.out.println(hey.length);
        for(int i=0; i<hey.length;i++) {
            System.out.println(hey[i]);
        }*/

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

    @FXML
    void chooseHumidityChart(ActionEvent event) throws SQLException {
        description.setText("Weather chart");

        Stage stage = new Stage();
        final NumberAxis xAxis = new NumberAxis(1, 5, 1);
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Days of forecast");
        yAxis.setLabel("%");

        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Humidity Chart");
        XYChart.Series series = new XYChart.Series();


        String city = cityName.getText();
        String country = countryName.getText();
        label1.setText("");
        label2.setText("");

        GetWeather getWeather= new GetWeather();
        Scene scene = new Scene(lineChart, 775, 505);
        if(checkConditions(city, country)){

                try {
                    ArrayList<String> ar = getWeather.getForecast(city, country);
                    String tmp1 = ar.get(3);
                    String tmp2 = ar.get(10);
                    String tmp3 = ar.get(17);
                    String tmp4 = ar.get(24);
                    String tmp5 = ar.get(31);
                    double tmpD1 = Double.parseDouble(tmp1);
                    double tmpD2 = Double.parseDouble(tmp2);
                    double tmpD3 = Double.parseDouble(tmp3);
                    double tmpD4 = Double.parseDouble(tmp4);
                    double tmpD5 = Double.parseDouble(tmp5);
                    if(hello==1) {
                        PostgresHelper client = new PostgresHelper(
                                DbContract.HOST,
                                DbContract.DB_NAME,
                                DbContract.USERNAME,
                                DbContract.PASSWORD);

                        try {
                            if (client.connect()) {
                                System.out.println("DB connected");
                            }

                        } catch ( SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e){
                            e.printStackTrace();
                        }
                        String[] hey = new String[5];
                        String[] temp;
                        for(int i=0;i<5;i++) {
                            System.out.println(getMeXDayBackDate(i).toString());
                            temp = client.select(getMeXDayBackDate(i).toString(), city + " " + country);
                            for(int j=0;j<temp.length;j++){
                                System.out.println(temp[j]);
                            }
                            hey[i] = temp[4];
                        }

                        tmp1 ="0";tmp2 ="0";tmp3 ="0";tmp4 ="0";tmp5 ="0";
                        if(hey[4] != null && !hey[4].equals("")){tmp1 = hey[4];}
                        if(hey[3] != null && !hey[3].equals("")){tmp2 = hey[3];}
                        if(hey[2] != null && !hey[2].equals("")){tmp3 = hey[2];}
                        if(hey[1] != null && !hey[1].equals("")){tmp4 = hey[1];}
                        if(hey[0] != null && !hey[0].equals("")){tmp5 = hey[0];}
                         tmpD1 = Double.parseDouble(tmp1);
                         tmpD2 = Double.parseDouble(tmp2);
                         tmpD3 = Double.parseDouble(tmp3);
                         tmpD4 = Double.parseDouble(tmp4);
                         tmpD5 = Double.parseDouble(tmp5);


                    }

                    series.getData().add(new XYChart.Data(1, tmpD1));
                    series.getData().add(new XYChart.Data(2, tmpD2));
                    series.getData().add(new XYChart.Data(3, tmpD3));
                    series.getData().add(new XYChart.Data(4, tmpD4));
                    series.getData().add(new XYChart.Data(5, tmpD5));
                    lineChart.getData().add(series);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "You entered incorrect city or country",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
    }

    @FXML
    void choosePressureChart(ActionEvent event) {
        description.setText("Weather chart");

        Stage stage = new Stage();
        final NumberAxis xAxis = new NumberAxis(1, 5, 1);
        final NumberAxis yAxis = new NumberAxis(950, 1050, 5);
        xAxis.setLabel("Days of forecast");

        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Pressure Chart");
        XYChart.Series series = new XYChart.Series();


        String city = cityName.getText();
        String country = countryName.getText();
        label1.setText("");
        label2.setText("");

        GetWeather getWeather= new GetWeather();
        Scene scene = new Scene(lineChart, 640, 480);
        if(checkConditions(city, country)){
            try {
                ArrayList<String> ar = getWeather.getForecast(city, country);
                String tmp1 = ar.get(2);
                String tmp2 = ar.get(9);
                String tmp3 = ar.get(16);
                String tmp4 = ar.get(23);
                String tmp5 = ar.get(30);
                double tmpD1 = Double.parseDouble(tmp1);
                double tmpD2 = Double.parseDouble(tmp2);
                double tmpD3 = Double.parseDouble(tmp3);
                double tmpD4 = Double.parseDouble(tmp4);
                double tmpD5 = Double.parseDouble(tmp5);
                if(hello==1) {
                    PostgresHelper client = new PostgresHelper(
                            DbContract.HOST,
                            DbContract.DB_NAME,
                            DbContract.USERNAME,
                            DbContract.PASSWORD);

                    try {
                        if (client.connect()) {
                            System.out.println("DB connected");
                        }

                    } catch ( SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e){
                        e.printStackTrace();
                    }
                    String[] hey = new String[5];
                    String[] temp;
                    for(int i=0;i<5;i++) {
                        System.out.println(getMeXDayBackDate(i).toString());
                        temp = client.select(getMeXDayBackDate(i).toString(), city + " " + country);
                        for(int j=0;j<temp.length;j++){
                            System.out.println(temp[j]);
                        }
                        hey[i] = temp[5];
                    }

                    tmp1 ="0";tmp2 ="0";tmp3 ="0";tmp4 ="0";tmp5 ="0";
                    if(hey[4] != null && !hey[4].equals("")){tmp1 = hey[4];}
                    if(hey[3] != null && !hey[3].equals("")){tmp2 = hey[3];}
                    if(hey[2] != null && !hey[2].equals("")){tmp3 = hey[2];}
                    if(hey[1] != null && !hey[1].equals("")){tmp4 = hey[1];}
                    if(hey[0] != null && !hey[0].equals("")){tmp5 = hey[0];}
                    tmpD1 = Double.parseDouble(tmp1);
                    tmpD2 = Double.parseDouble(tmp2);
                    tmpD3 = Double.parseDouble(tmp3);
                    tmpD4 = Double.parseDouble(tmp4);
                    tmpD5 = Double.parseDouble(tmp5);


                }
                series.getData().add(new XYChart.Data(1, tmpD1));
                series.getData().add(new XYChart.Data(2, tmpD2));
                series.getData().add(new XYChart.Data(3, tmpD3));
                series.getData().add(new XYChart.Data(4, tmpD4));
                series.getData().add(new XYChart.Data(5, tmpD5));
                lineChart.getData().add(series);
                stage.setScene(scene);
                stage.show();
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
    void chooseTemperatureChart(ActionEvent event) {
        description.setText("Weather chart");

        Stage stage = new Stage();
        final NumberAxis xAxis = new NumberAxis(1, 5, 1);
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Days of forecast");

        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Temperature Chart");
        XYChart.Series series = new XYChart.Series();


        String city = cityName.getText();
        String country = countryName.getText();
        label1.setText("");
        label2.setText("");

        GetWeather getWeather= new GetWeather();
        Scene scene = new Scene(lineChart, 640, 480);
        if(checkConditions(city, country)){
            try {
                ArrayList<String> ar = getWeather.getForecast(city, country);
                String tmp1 = ar.get(1);
                String tmp2 = ar.get(8);
                String tmp3 = ar.get(15);
                String tmp4 = ar.get(22);
                String tmp5 = ar.get(29);
                double tmpD1 = Double.parseDouble(tmp1);
                double tmpD2 = Double.parseDouble(tmp2);
                double tmpD3 = Double.parseDouble(tmp3);
                double tmpD4 = Double.parseDouble(tmp4);
                double tmpD5 = Double.parseDouble(tmp5);
                if(hello==1) {
                    PostgresHelper client = new PostgresHelper(
                            DbContract.HOST,
                            DbContract.DB_NAME,
                            DbContract.USERNAME,
                            DbContract.PASSWORD);

                    try {
                        if (client.connect()) {
                            System.out.println("DB connected");
                        }

                    } catch ( SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e){
                        e.printStackTrace();
                    }
                    String[] hey = new String[5];
                    String[] temp;
                    for(int i=0;i<5;i++) {
                        System.out.println(getMeXDayBackDate(i).toString());
                        temp = client.select(getMeXDayBackDate(i).toString(), city + " " + country);
                        for(int j=0;j<temp.length;j++){
                            System.out.println(temp[j]);
                        }
                        hey[i] = temp[3];
                    }

                    tmp1 ="0";tmp2 ="0";tmp3 ="0";tmp4 ="0";tmp5 ="0";
                    if(hey[4] != null && !hey[4].equals("")){tmp1 = hey[4];}
                    if(hey[3] != null && !hey[3].equals("")){tmp2 = hey[3];}
                    if(hey[2] != null && !hey[2].equals("")){tmp3 = hey[2];}
                    if(hey[1] != null && !hey[1].equals("")){tmp4 = hey[1];}
                    if(hey[0] != null && !hey[0].equals("")){tmp5 = hey[0];}
                    tmpD1 = Double.parseDouble(tmp1);
                    tmpD2 = Double.parseDouble(tmp2);
                    tmpD3 = Double.parseDouble(tmp3);
                    tmpD4 = Double.parseDouble(tmp4);
                    tmpD5 = Double.parseDouble(tmp5);


                }
                series.getData().add(new XYChart.Data(1, tmpD1));
                series.getData().add(new XYChart.Data(2, tmpD2));
                series.getData().add(new XYChart.Data(3, tmpD3));
                series.getData().add(new XYChart.Data(4, tmpD4));
                series.getData().add(new XYChart.Data(5, tmpD5));
                lineChart.getData().add(series);
                stage.setScene(scene);
                stage.show();
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
    void chooseWindChart(ActionEvent event) {
        description.setText("Weather chart");

        Stage stage = new Stage();
        final NumberAxis xAxis = new NumberAxis(1, 5, 1);
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Days of forecast");

        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Wind Chart");
        XYChart.Series series = new XYChart.Series();


        String city = cityName.getText();
        String country = countryName.getText();
        label1.setText("");
        label2.setText("");

        GetWeather getWeather= new GetWeather();
        Scene scene = new Scene(lineChart, 640, 480);
        if(checkConditions(city, country)){
            try {
                ArrayList<String> ar = getWeather.getForecast(city, country);
                String tmp1 = ar.get(5);
                String tmp2 = ar.get(12);
                String tmp3 = ar.get(19);
                String tmp4 = ar.get(26);
                String tmp5 = ar.get(33);
                double tmpD1 = Double.parseDouble(tmp1);
                double tmpD2 = Double.parseDouble(tmp2);
                double tmpD3 = Double.parseDouble(tmp3);
                double tmpD4 = Double.parseDouble(tmp4);
                double tmpD5 = Double.parseDouble(tmp5);
                if(hello==1) {
                    PostgresHelper client = new PostgresHelper(
                            DbContract.HOST,
                            DbContract.DB_NAME,
                            DbContract.USERNAME,
                            DbContract.PASSWORD);

                    try {
                        if (client.connect()) {
                            System.out.println("DB connected");
                        }

                    } catch ( SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e){
                        e.printStackTrace();
                    }
                    String[] hey = new String[5];
                    String[] temp;
                    for(int i=0;i<5;i++) {
                        System.out.println(getMeXDayBackDate(i).toString());
                        temp = client.select(getMeXDayBackDate(i).toString(), city + " " + country);
                        for(int j=0;j<temp.length;j++){
                            System.out.println(temp[j]);
                        }
                        hey[i] = temp[7];
                    }

                    tmp1 ="0";tmp2 ="0";tmp3 ="0";tmp4 ="0";tmp5 ="0";
                    if(hey[4] != null && !hey[4].equals("")){tmp1 = hey[4];}
                    if(hey[3] != null && !hey[3].equals("")){tmp2 = hey[3];}
                    if(hey[2] != null && !hey[2].equals("")){tmp3 = hey[2];}
                    if(hey[1] != null && !hey[1].equals("")){tmp4 = hey[1];}
                    if(hey[0] != null && !hey[0].equals("")){tmp5 = hey[0];}
                    tmpD1 = Double.parseDouble(tmp1);
                    tmpD2 = Double.parseDouble(tmp2);
                    tmpD3 = Double.parseDouble(tmp3);
                    tmpD4 = Double.parseDouble(tmp4);
                    tmpD5 = Double.parseDouble(tmp5);


                }
                series.getData().add(new XYChart.Data(1, tmpD1));
                series.getData().add(new XYChart.Data(2, tmpD2));
                series.getData().add(new XYChart.Data(3, tmpD3));
                series.getData().add(new XYChart.Data(4, tmpD4));
                series.getData().add(new XYChart.Data(5, tmpD5));
                lineChart.getData().add(series);
                stage.setScene(scene);
                stage.show();
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
}