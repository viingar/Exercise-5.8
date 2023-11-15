package com.example.weathermicroservice.Controller;

import com.example.weathermicroservice.Model.Weather;
import com.example.weathermicroservice.Service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping("/{latitude}/{longitude}")
    public Weather getWeather(@PathVariable double latitude, @PathVariable double longitude, @Value("${openweathermap.apiKey}") String apiKey) {
        try {
            Call<Weather> call = weatherService.getWeatherByCoordinates(latitude, longitude, apiKey);
            Response<Weather> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
