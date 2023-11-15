package com.example.weathermicroservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Main {

    private double temp;
    private double humidity;
    private double pressure;
    private double temp_max;
    private double sea_level;
    private double grnd_level;
    private double feels_like;


}