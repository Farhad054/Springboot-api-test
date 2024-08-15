package com.guliyev.app.rest.exceptionHandle;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ErrorResponse {
    private final String message;
    private HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ErrorResponse(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
