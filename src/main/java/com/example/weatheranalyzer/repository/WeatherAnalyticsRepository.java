package com.example.weatheranalyzer.repository;

import com.example.weatheranalyzer.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherAnalyticsRepository extends JpaRepository<Weather, Long> {

    List<Weather> findAllByCreateDateBetween(LocalDateTime periodStartDate, LocalDateTime periodEndDate);
}
