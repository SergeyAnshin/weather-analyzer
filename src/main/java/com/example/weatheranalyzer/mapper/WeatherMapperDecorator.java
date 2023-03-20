package com.example.weatheranalyzer.mapper;

import com.example.weatheranalyzer.dto.WeatherApiCondition;
import com.example.weatheranalyzer.dto.WeatherApiResponseBody;
import com.example.weatheranalyzer.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class WeatherMapperDecorator implements WeatherMapper {
    @Autowired
    @Qualifier("delegate")
    private WeatherMapper delegate;

    @Override
    public Weather responseBodyToWeather(WeatherApiResponseBody weatherApiResponseBody) {
        WeatherApiCondition weatherCondition = weatherApiResponseBody.getCurrent().getCondition();
        String upperCaseText = weatherCondition.getText().toUpperCase();
        weatherCondition.setText(upperCaseText);
        return delegate.responseBodyToWeather(weatherApiResponseBody);
    }
}
