package com.example.weatheranalyzer.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class WeatherApiResponseBody {
    private WeatherApiLocation location;
    private WeatherApiCurrentWeather current;
}
