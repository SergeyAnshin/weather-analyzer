package com.example.weatheranalyzer.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class WeatherApiError {
    private int code;
    private String message;
}
