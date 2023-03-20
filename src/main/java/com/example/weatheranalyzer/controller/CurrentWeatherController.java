package com.example.weatheranalyzer.controller;

import com.example.weatheranalyzer.dto.CurrentWeatherResponse;
import com.example.weatheranalyzer.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/current-weather")
@RequiredArgsConstructor
public class CurrentWeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeatherInformation() {
        return ResponseEntity.ok(weatherService.getCurrentWeather());
    }
}
