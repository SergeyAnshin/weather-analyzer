package com.example.weatheranalyzer.exception;

public class WeatherApiUnknownErrorCodeException extends RuntimeException {

    public WeatherApiUnknownErrorCodeException(String message) {
        super(message);
    }
}
