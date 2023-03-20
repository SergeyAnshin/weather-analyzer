package com.example.weatheranalyzer.controller;

import com.example.weatheranalyzer.service.WeatherAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/weather-analytics")
@RequiredArgsConstructor
public class WeatherAnalyticsController {
    private final WeatherAnalyticsService analyticsService;

    @GetMapping("/avg-daily-temp")
    public ResponseEntity<Object> getAverageDailyTemperatureForPeriod(@RequestParam("from") LocalDate periodStartDate,
                                                                      @RequestParam("to") LocalDate periodEndDate) {
        return ResponseEntity.ok(analyticsService.getAverageDailyTemperatureForPeriod(periodStartDate, periodEndDate));
    }
}
