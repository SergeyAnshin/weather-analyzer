package com.example.weatheranalyzer.exception.handler;


public interface ErrorResponseBodyGenerator {

    ErrorResponseBody generateFrom(Exception exception);
}
