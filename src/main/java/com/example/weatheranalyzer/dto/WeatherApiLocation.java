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
public class WeatherApiLocation {
    private String name;
    private String region;
    private String country;
    private String lat;
    private String lon;
    private String tzId;
    private long localtimeEpoch;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime localtime;
}
