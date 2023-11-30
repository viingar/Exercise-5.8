package com.example.locationmicroservice.Controller;

import com.example.locationmicroservice.Model.Geodata;
import com.example.locationmicroservice.Model.Weather;
import com.example.locationmicroservice.Repository.GeodataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class WeatherController {

    @Autowired
    private GeodataRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${weather.url}")
    String weatherUrl;

    @GetMapping("/weather")
    public ResponseEntity<?> redirectRequestWeather(@RequestParam String location) {
        Optional<Geodata> optionalGeodata = repository.findByName(location);

        if (!optionalGeodata.isPresent()) {
            return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
        }

        Geodata geodata = optionalGeodata.get();
        String url = String.format("http://weather-info-service/?lat=%s&lon=%s",  geodata.getLat(), geodata.getLon());
        try {
            Weather weather = restTemplate.getForObject(url, Weather.class);
            return new ResponseEntity<>(weather, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching weather data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public Optional<Geodata> getWeather(@RequestParam String location) {
        return repository.findByName(location);
    }

    @PostMapping
    public Geodata save(@RequestBody Geodata geodata) {
        return repository.save(geodata);
    }

}