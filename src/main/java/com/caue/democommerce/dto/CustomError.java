package com.caue.democommerce.dto;

import java.time.Instant;

public class CustomError {

    private Instant timestamp;

    private Integer statusCode;

    private String error;

    private String path;

    public CustomError(Instant timestamp, Integer statusCode, String error, String path) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
