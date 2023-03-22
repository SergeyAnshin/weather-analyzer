package com.example.weatheranalyzer.exception;

import com.example.weatheranalyzer.dto.WeatherApiErrorResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@Component
@RequiredArgsConstructor
public class WeatherApiErrorHandler {
    private static final int API_KEY_NOT_PROVIDED_CODE = 1002;
    private static final int PARAMETER_Q_NOT_PROVIDED_CODE = 1003;
    private static final int INVALID_API_REQUEST_URL_CODE = 1005;
    private static final int NO_LOCATION_CODE = 1006;
    private static final int INVALID_API_KEY_CODE = 2006;
    public static final int CALL_QUOTA_EXCEEDED_CODE = 2007;
    public static final int DISABLED_API_KEY_CODE = 2008;
    public static final int ACCESS_TO_RESOURCE_WITH_KEY_NOT_ALLOWED_CODE = 2009;
    public static final int INVALID_BULK_REQUEST_CODE = 9000;
    public static final int TOO_MANY_LOCATIONS_CODE = 9001;
    public static final int INTERNAL_APPLICATION_ERROR_CODE = 9999;
    private final ObjectMapper objectMapper;

    public void handleError(HttpStatusCodeException response) {
        String responseBodyAsString = response.getResponseBodyAsString();
        WeatherApiErrorResponseBody responseBody;
        try {
            responseBody = objectMapper.readValue(responseBodyAsString,
                    WeatherApiErrorResponseBody.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        switch (responseBody.getError().getCode()) {
            case API_KEY_NOT_PROVIDED_CODE -> throw new WeatherApiKeyException("""
                    The Weather API requires the API key.
                    Check if the key is in a query parameter
                    """);
            case PARAMETER_Q_NOT_PROVIDED_CODE -> throw new WeatherApiParamException("""
                    The Weather API requires the 'q' parameter in a query parameter.
                    Check if request contains it
                    """);
            case INVALID_API_REQUEST_URL_CODE -> throw new WeatherApiParamException("""
                    The Weather API doesn't have URL
                     """);
            case NO_LOCATION_CODE -> throw new WeatherApiParamException("""
                    The city doesn't exist or the Weather API doesn't support a location.
                    Check if location in the application.properties is valid
                    """);
            case INVALID_API_KEY_CODE -> throw new WeatherApiKeyException("""
                    Invalid API key.
                    Compare the API key used in the application with the key in the account
                    """);
            case CALL_QUOTA_EXCEEDED_CODE -> throw new WeatherApiKeyException("""
                    The number of requests to the Weather API has exceeded the allowable limit
                    """);
            case DISABLED_API_KEY_CODE -> throw new WeatherApiKeyException("""
                    The API key isn't valid.
                    Get a new key in account
                    """);
            case ACCESS_TO_RESOURCE_WITH_KEY_NOT_ALLOWED_CODE -> throw new WeatherApiKeyException("""
                    The Weather API provides different subscription plans.
                    Check if access to the resource is included in the subscription plan
                    """);
            case INVALID_BULK_REQUEST_CODE -> throw new WeatherApiResponseBodyException("""
                    Invalid json body
                    """);
            case TOO_MANY_LOCATIONS_CODE -> throw new WeatherApiParamException("""
                    The number of locations in the request should not exceed 50
                    """);
            case INTERNAL_APPLICATION_ERROR_CODE -> throw new WeatherApiInternalException("""
                    Error on the Weather API side
                    """);
            default -> throw new WeatherApiUnknownErrorCodeException("""
                    Unknown error.
                    Check the Weather API documentation
                    """);
        }
    }
}
