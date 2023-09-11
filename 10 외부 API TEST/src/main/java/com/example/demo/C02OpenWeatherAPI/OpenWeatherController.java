package com.example.demo.C02OpenWeatherAPI;

//35.847718   128.558297
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/weather")
public class OpenWeatherController {

    private final String API_KEY = "63f1ce0f9cd90d936d4f94f4fd85bc9b";

    @GetMapping("/get/{lat}/{lon}")
    public Object getWeather(@PathVariable String lat, @PathVariable String lon) {
        //URL
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+API_KEY;
        //HEADER

        //PARAMETER

        //CALL_01(기존방식 JSON파싱)
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> Response = rt.exchange(url, HttpMethod.GET, null, String.class);
//        System.out.println(Response);
//        System.out.println(Response.getBody());
//        return Response.getBody();

        //CALL_02
        RestTemplate rt = new RestTemplate();
        WeatherResponse response = rt.getForObject(url, WeatherResponse.class);
        System.out.println(response);
        return response;


    }


}
@Data
class WeatherResponse {
    Coord Coord;
    ArrayList< Object > weather = new ArrayList < Object > ();
    private String base;
    Main main;
    private float visibility;
    Wind wind;
    Rain rain;
    Clouds clouds;
    private float dt;
    Sys sys;
    private float timezone;
    private float id;
    private String name;
    private float cod;

    @Data
    class Sys {
        private float type;
        private float id;
        private String country;
        private float sunrise;
        private float sunset;
    }
    @Data
    class Clouds {
        private float all;
    }
    @Data
    class Rain {
        @JsonProperty("1h")
        private float h1;
    }
    @Data
    class Wind {
        private float speed;
        private float deg;
    }
    @Data
    class Main {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private float pressure;
        private float humidity;
    }
    @Data
    class Coord {
        private float lon;
        private float lat;
    }
}

