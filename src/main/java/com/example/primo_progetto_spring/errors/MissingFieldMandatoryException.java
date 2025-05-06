package com.example.primo_progetto_spring.errors;

public class MissingFieldMandatoryException extends ResponseException{
    private final String field;

    public MissingFieldMandatoryException(String field){
        super("error: missing field: '" + field + "'");
        this.field = field;
    }

    @Override
    public ErrorResponseDTO toErrorDTO() {
        return new ErrorResponseDTO(super.getMessage());
    }

    public String getField() {
        return field;
    }

}
