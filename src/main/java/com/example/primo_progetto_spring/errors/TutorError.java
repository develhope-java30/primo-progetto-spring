package com.example.primo_progetto_spring.errors;

import java.time.LocalDateTime;

//https://github.com/bezkoder/spring-boot-restcontrolleradvice/blob/master/src/main/java/com/bezkoder/spring/rest/exhandling/exception/ErrorMessage.java

public class TutorError {
    private LocalDateTime timestamp;
    private String message;
    private String field;

    public TutorError(LocalDateTime timestamp, String message, String field) {
        this.timestamp = timestamp;
        this.message = message;
        this.field = field;
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
