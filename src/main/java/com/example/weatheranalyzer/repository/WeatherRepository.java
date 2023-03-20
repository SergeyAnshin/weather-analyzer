package com.example.weatheranalyzer.repository;

import com.example.weatheranalyzer.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findTopByLocationCityOrderByCreateDateDesc(String city);
}
