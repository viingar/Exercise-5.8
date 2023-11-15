package com.example.weathermicroservice.Service;

import com.example.weathermicroservice.Model.Weather;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class WeatherServiceImplementation implements WeatherService {

    private WeatherService weatherService;

    public WeatherServiceImplementation() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.weatherService = retrofit.create(WeatherService.class);
    }

    @Override
    public Call<Weather> getWeatherByCoordinates(double latitude, double longitude, String apiKey) {
        return weatherService.getWeatherByCoordinates(latitude,longitude,apiKey);
    }
}
