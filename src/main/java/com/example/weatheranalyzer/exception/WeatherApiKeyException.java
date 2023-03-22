package com.example.weatheranalyzer.exception;

public class WeatherApiKeyException extends CriticalApplicationException {

    public WeatherApiKeyException(String message) {
        super(message);
    }

}
