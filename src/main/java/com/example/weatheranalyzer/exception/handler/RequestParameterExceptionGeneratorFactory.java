package com.example.weatheranalyzer.exception.handler;

import org.springframework.stereotype.Component;

@Component
public class RequestParameterExceptionGeneratorFactory extends ErrorResponseBodyGeneratorFactory {
    public static final String QUALIFIER = "RequestParameterExceptionGeneratorFactory";

    @Override
    protected ErrorResponseBodyGenerator createErrorResponseBodyGenerator() {
        return new RequestParameterExceptionGenerator();
    }
}
