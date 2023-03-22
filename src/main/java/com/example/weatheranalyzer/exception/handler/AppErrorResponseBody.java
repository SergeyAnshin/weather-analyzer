package com.example.weatheranalyzer.exception.handler;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class AppErrorResponseBody implements ErrorResponseBody {
    private int code;
    private String message;
}
