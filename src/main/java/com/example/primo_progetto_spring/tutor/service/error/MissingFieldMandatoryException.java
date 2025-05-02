package com.example.primo_progetto_spring.tutor.service.error;

public class MissingFieldMandatoryException extends RuntimeException{
    private final String field;

    public MissingFieldMandatoryException(String field){
        super("error: missing filed.");
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
