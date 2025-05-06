package com.example.primo_progetto_spring.errors;

public class NotAssignedCourseException extends ResponseException{
    private final Long classroomId;

    public NotAssignedCourseException(Long classRoomId) {
        super("Impossibile aggiungere lo studente alla classroom perché non c'è nessun corso assegnato, classe: '" + classRoomId + "'");
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
