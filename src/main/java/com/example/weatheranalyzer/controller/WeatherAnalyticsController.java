package com.example.weatheranalyzer.controller;

import com.example.weatheranalyzer.service.WeatherAnalyticsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/weather-analytics")
@RequiredArgsConstructor
@Validated
public class WeatherAnalyticsController {
    private final WeatherAnalyticsService analyticsService;

    @GetMapping("/avg-daily-temp")
    public ResponseEntity<Object> getAverageDailyTemperatureForPeriod(
            @RequestParam("from") @Valid @PastOrPresent LocalDate periodStartDate,
            @RequestParam("to") @Valid @PastOrPresent LocalDate periodEndDate) {
        return ResponseEntity.ok(analyticsService.getAverageDailyTemperatureForPeriod(periodStartDate, periodEndDate));
    }
}
