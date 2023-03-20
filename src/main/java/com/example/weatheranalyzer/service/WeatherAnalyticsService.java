package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.dto.AnalyticalWeatherIndicators;
import com.example.weatheranalyzer.entity.Weather;
import com.example.weatheranalyzer.exception.WeatherNotFoundException;
import com.example.weatheranalyzer.repository.WeatherAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherAnalyticsService {
    private final WeatherAnalyticsRepository analyticsRepository;

    public AnalyticalWeatherIndicators getAverageDailyTemperatureForPeriod(LocalDate periodStartDate,
                                                                           LocalDate periodEndDate) {
        LocalDateTime startDate = LocalDateTime.of(periodStartDate, LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(periodEndDate, LocalTime.MIN);
        List<Weather> weatherSlice = analyticsRepository.findAllByCreateDateBetween(startDate, endDate);
        double averageDailyTemperature = calculateAverageDailyTemperature(weatherSlice);
        return AnalyticalWeatherIndicators.builder().averageDailyTemperature((float) averageDailyTemperature).build();
    }

    private double calculateAverageDailyTemperature(List<Weather> weatherSlice) {
        return weatherSlice.stream()
                .mapToDouble((weather) -> (double) weather.getTemperature())
                .average()
                .orElseThrow(() -> new WeatherNotFoundException("""
                        Error in calculating the average daily temperature.
                        No weather information found for this period
                        """));
    }
}
