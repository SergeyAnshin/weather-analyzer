package com.example.weatheranalyzer.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class WeatherApiCondition {
    private String text;
    private String icon;
    private int code;
}
