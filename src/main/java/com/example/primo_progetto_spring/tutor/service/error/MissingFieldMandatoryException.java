package com.example.primo_progetto_spring.tutor.service.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"stackTrace", "cause", "suppressed", "localizedMessage"})
public class MissingFieldMandatoryException extends Exception{
    private String field;

    public MissingFieldMandatoryException(String field){
        super("missing filed.");
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
