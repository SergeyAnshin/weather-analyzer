package com.example.weatheranalyzer.exception;

public class CriticalApplicationException extends RuntimeException {

    public CriticalApplicationException(String message) {
        super(message);
    }
}
