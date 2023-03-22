package com.example.weatheranalyzer.exception;

public class WeatherApiParamException extends CriticalApplicationException {

    public WeatherApiParamException(String message) {
        super(message);
    }
}
