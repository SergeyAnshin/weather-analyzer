package com.example.weatheranalyzer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WeatherApiCurrentWeather {
    private long lastUpdatedEpoch;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastUpdated;
    private float tempC;
    private float tempF;
    private int isDay;
    private WeatherApiCondition condition;
    private float windMph;
    private float windKph;
    private float windDegree;
    private String windDir;
    private float pressureMb;
    private float pressureIn;
    private float precipMm;
    private float precipIn;
    private float humidity;
    private float cloud;
    private float feelslikeC;
    private float feelslikeF;
    private float visKm;
    private float visMiles;
    private float uv;
    private float gustMph;
    private float gustKph;
}
