package com.example.primo_progetto_spring.tutor.service.error;

import java.time.LocalDateTime;

//https://github.com/bezkoder/spring-boot-restcontrolleradvice/blob/master/src/main/java/com/bezkoder/spring/rest/exhandling/exception/ErrorMessage.java

public class TutorError {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String field;

    public TutorError(int statusCode, LocalDateTime timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.field = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

}
