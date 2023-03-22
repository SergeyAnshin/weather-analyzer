package com.example.weatheranalyzer.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class WeatherApiErrorResponseBody {
    private WeatherApiError error;
}
