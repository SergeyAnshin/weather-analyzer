package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.dto.WeatherApiResponseBody;
import com.example.weatheranalyzer.entity.Location;
import com.example.weatheranalyzer.entity.Weather;
import com.example.weatheranalyzer.mapper.WeatherMapper;
import com.example.weatheranalyzer.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherService {
    @Value("${webclient.weather-api.realtime-api.query-param.name}")
    private String realtimeApiQueryParamName;
    @Value("${webclient.weather-api.realtime-api.query-param.value}")
    private String realtimeApiQueryParamValue;
    private final LocationService locationService;
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;
    private final WebClient webClient;

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
        WeatherApiResponseBody weatherApiResponseBody = webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam(realtimeApiQueryParamName, realtimeApiQueryParamValue).build())
                .retrieve()
                .bodyToMono(WeatherApiResponseBody.class)
                .block();
        save(weatherApiResponseBody);
    }

    public Weather getCurrentWeather() {
        return weatherRepository.findTopByLocationCityOrderByCreateDateDesc(realtimeApiQueryParamValue).orElseThrow();
    }
}
