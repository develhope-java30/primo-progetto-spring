package com.example.primo_progetto_spring.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"stackTrace", "cause", "suppressed", "localizedMessage"})
public class NotAssignedCourseException extends RuntimeException{
    private final Long classroomId;

    public NotAssignedCourseException(Long classRoomId) {
        super("Impossibile aggiungere lo studente alla classroom perchè non c'è nessun corso assegnato!");
        this.classroomId = classRoomId;
    }

    public Long getClassRoomId() {
        return classroomId;
    }
}
