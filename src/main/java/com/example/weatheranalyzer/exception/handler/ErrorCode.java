package com.example.weatheranalyzer.exception.handler;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_QUERY_PARAM(10);

    private int code;

    ErrorCode(int code) {

    }
}
