package com.example.weatheranalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class AnalyticalWeatherIndicators {
    @JsonProperty("average_temp")
    private float averageDailyTemperature;
}
