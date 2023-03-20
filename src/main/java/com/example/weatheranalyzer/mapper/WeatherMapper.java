package com.example.weatheranalyzer.mapper;

import com.example.weatheranalyzer.dto.CurrentWeatherResponse;
import com.example.weatheranalyzer.dto.WeatherApiResponseBody;
import com.example.weatheranalyzer.entity.Weather;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
@DecoratedWith(WeatherMapperDecorator.class)
public interface WeatherMapper {
    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    @Mappings({
            @Mapping(target = "temperature", source = "current.tempC"),
            @Mapping(target = "windVelocity", source = "current.windMph"),
            @Mapping(target = "atmosphericPressure", source = "current.pressureMb"),
            @Mapping(target = "humidity", source = "current.humidity"),
            @Mapping(target = "condition.weatherType", source = "current.condition.text"),
            @Mapping(target = "location.city", source = "location.name"),
    })
    Weather responseBodyToWeather(WeatherApiResponseBody weatherApiResponseBody);

    CurrentWeatherResponse weatherToCurrentWeatherResponse(Weather weather);
}
