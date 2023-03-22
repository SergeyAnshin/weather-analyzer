package com.example.weatheranalyzer.exception;

public class WeatherApiResponseBodyException extends CriticalApplicationException {

    public WeatherApiResponseBodyException(String message) {
        super(message);
    }
}
