package com.example.weatheranalyzer.exception.handler;

public abstract class ErrorResponseBodyGeneratorFactory {


    public ErrorResponseBody generateFrom(Exception exception) {
        ErrorResponseBodyGenerator errorResponseBodyGenerator = createErrorResponseBodyGenerator();
        return errorResponseBodyGenerator.generateFrom(exception);
    }

    protected abstract ErrorResponseBodyGenerator createErrorResponseBodyGenerator();
}
