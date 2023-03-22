package com.example.weatheranalyzer.controller;

import com.example.weatheranalyzer.exception.handler.ErrorResponseBodyGeneratorFactory;
import com.example.weatheranalyzer.exception.handler.RequestParameterExceptionGeneratorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    private final Map<String, ErrorResponseBodyGeneratorFactory> errorResponseBodyGenerators;

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatusCode status,
                                                                          WebRequest request) {
        return ResponseEntity.badRequest()
                .body(errorResponseBodyGenerators.get(RequestParameterExceptionGeneratorFactory.QUALIFIER)
                        .generateFrom(ex));
    }
}
