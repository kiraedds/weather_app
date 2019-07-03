import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.scene.image.Image;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class GetWeather {

public String[] getCurrentWeather(String city,String country)
{
    String ApiKey= "028b703842e22532bb24f05d7e42e17a";
    String location= city+","+country;
    String urlString = "https://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+ApiKey;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(urlString, String.class);
    String[] weather=new String[7];
    if (response.getStatusCode() == HttpStatus.OK)
    {
        String result = response.getBody();
        JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
        weather=getData(jsonObject);
    }
    return weather;
}

public ArrayList<String> getForecast(String city,String country)
{
    String[] weather =new String [7];
    int i=0;
    String ApiKey= "028b703842e22532bb24f05d7e42e17a";
    String location= city+","+country;
    String urlString = "https://api.openweathermap.org/data/2.5/forecast?q="+location+"&appid="+ApiKey;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(urlString, String.class);
    ArrayList<String> ar = new ArrayList<String>();
    if (response.getStatusCode() == HttpStatus.OK)
    {
        String result = response.getBody();
        System.out.println(result);
        JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
        JsonArray dateList = (JsonArray) jsonObject.get("list");
        while(i<40)
        {
            if(i%8==0) {
                JsonObject mainArray = (JsonObject) dateList.get(i);
                weather=getData(mainArray);
                for(int j=0;j<7;j++)
                {
                    ar.add(weather[j]);
                }
            }
            i++;
        }
    }
return ar;
}



public String[] getData(JsonObject jsonObject)
{
    String[] data=new String[7];
    JsonArray weather = (JsonArray) jsonObject.get("weather");
    JsonObject weatherObj = (JsonObject) weather.get(0);
    JsonObject main = (JsonObject) jsonObject.get("main");
    JsonObject wind = (JsonObject) jsonObject.get("wind");

    data[0]=weatherObj.get("description").toString();
    data[1]=main.get("temp").toString();
    data[2]=main.get("pressure").toString();
    data[3]=main.get("humidity").toString();
    data[4]=wind.get("deg").toString();
    data[5]=wind.get("speed").toString();
    data[6]=weatherObj.get("icon").toString();

    double d =Double.parseDouble(data[1]);
    d = d - 273.15;
    data[1] = Double.toString(d);
    return data;
}

    public Image getImage(String id)
    {
        Image image = new Image("http://openweathermap.org/img/w/"+id+".png");
        return image;
    }

}
