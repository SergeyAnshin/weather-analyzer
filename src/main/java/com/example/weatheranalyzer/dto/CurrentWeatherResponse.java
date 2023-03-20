package com.example.weatheranalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class CurrentWeatherResponse {
    private float temperature;
    private float windVelocity;
    private float atmosphericPressure;
    private byte humidity;
    private WeatherConditionResponse condition;
    private LocationResponse location;
}
