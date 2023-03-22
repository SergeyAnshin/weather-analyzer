package com.example.weatheranalyzer.exception.handler;

import org.springframework.web.bind.MissingServletRequestParameterException;

public class RequestParameterExceptionGenerator implements ErrorResponseBodyGenerator {

    @Override
    public ErrorResponseBody generateFrom(Exception exception) {
        MissingServletRequestParameterException ex = (MissingServletRequestParameterException) exception;
        return AppErrorResponseBody.builder()
                .code(ErrorCode.INVALID_QUERY_PARAM.getCode())
                .message(ex.getMessage())
                .build();
    }
}
