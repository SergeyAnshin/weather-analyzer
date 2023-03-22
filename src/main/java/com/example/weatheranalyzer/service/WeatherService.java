package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.dto.CurrentWeatherResponse;
import com.example.weatheranalyzer.dto.WeatherApiResponseBody;
import com.example.weatheranalyzer.entity.Location;
import com.example.weatheranalyzer.entity.Weather;
import com.example.weatheranalyzer.exception.CriticalApplicationException;
import com.example.weatheranalyzer.exception.WeatherApiErrorHandler;
import com.example.weatheranalyzer.mapper.WeatherMapper;
import com.example.weatheranalyzer.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Optional;

/**
 * Fix bug with deserialization LocalDateTime
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {
    @Value("${webclient.weather-api.realtime-api.query-param.city}")
    private String city;
    @Value("${webclient.weather-api.realtime-api.base-url}")
    private String realtimeWeatherApiBaseUrl;
    @Value("${webclient.weather-api.api-key}")
    private String apiKey;
    private final LocationService locationService;
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;
    private final WeatherApiErrorHandler weatherApiErrorHandler;

    public void save(WeatherApiResponseBody weatherApiResponseBody) {
        Weather currentWeather = weatherMapper.responseBodyToWeather(weatherApiResponseBody);
        persistLocation(currentWeather);
        weatherRepository.save(currentWeather);
    }

    private void persistLocation(Weather weather) {
        String city = weather.getLocation().getCity();
        Optional<Location> existingLocation = locationService.findByName(city);
        Location persistLocation = existingLocation.orElseGet(() -> locationService.save(weather.getLocation()));
        weather.setLocation(persistLocation);
    }

    @Scheduled(cron = "${weather-fetching-schedule}")
    private void fetchCurrentWeather() {
        try {
            WeatherApiResponseBody weatherApiResponseBody = new RestTemplateBuilder()
                    .build()
                    .getForEntity(realtimeWeatherApiBaseUrl.concat("?q={city}&key={key}&aqi=no"),
                            WeatherApiResponseBody.class, city, apiKey)
                    .getBody();
            save(weatherApiResponseBody);
        } catch (HttpStatusCodeException exception) {
            try {
                weatherApiErrorHandler.handleError(exception);
            } catch (CriticalApplicationException e) {
                log.error(e.getMessage());
                System.exit(0);
            }
        }
    }

    public CurrentWeatherResponse getCurrentWeather() {
        Weather currentWeather = weatherRepository.findTopByLocationCityOrderByCreateDateDesc(city)
                .orElseThrow();
        return weatherMapper.weatherToCurrentWeatherResponse(currentWeather);
    }
}
