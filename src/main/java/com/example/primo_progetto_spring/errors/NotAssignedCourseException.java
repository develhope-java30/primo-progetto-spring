package com.example.primo_progetto_spring.errors;

//@JsonIgnoreProperties(ignoreUnknown = true): Ignora tutte le proprietà sconosciute.
public class NotAssignedCourseException extends ResponseException{
    private final Long classroomId;

    public NotAssignedCourseException(Long classRoomId) {
        super("Impossibile aggiungere lo studente alla classroom perchè non c'è nessun corso assegnato!");
        this.classroomId = classRoomId;
    }

    public Long getClassRoomId() {
        return classroomId;
    }

    @Override
    public ErrorResponseDTO toErrorDTO() {
        return new ErrorResponseDTO(super.getMessage());
    }
}
