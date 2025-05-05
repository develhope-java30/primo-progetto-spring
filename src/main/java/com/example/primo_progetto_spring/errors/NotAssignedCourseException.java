package com.example.primo_progetto_spring.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true): Ignora tutte le proprietà sconosciute.
@JsonIgnoreProperties(ignoreUnknown = true) //value = {"stackTrace", "cause", "suppressed", "localizedMessage"}
public class NotAssignedCourseException extends RuntimeException{
    private final Long classroomId;

    public NotAssignedCourseException(Long classRoomId) {
        super("Impossibile aggiungere lo studente alla classroom perchè non c'è nessun corso assegnato!");
        this.classroomId = classRoomId;
    }

    //@JsonProperty("message"): Indica che il metodo getMessage()
    // deve essere serializzato con il nome message,
    // sovrascrivendo il comportamento predefinito di RuntimeException.
    @JsonProperty("message")
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Long getClassRoomId() {
        return classroomId;
    }
}
