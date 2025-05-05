package com.example.primo_progetto_spring.tutor.service.error;

import com.example.primo_progetto_spring.tutor.controller.TutorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//https://www.bezkoder.com/spring-boot-restcontrolleradvice/
//https://github.com/bezkoder/spring-boot-restcontrolleradvice

//assignableTypes ci permette di prendere l'eccezioni di solo questa classe per non incappare in eccezioni di altri controller.
@RestControllerAdvice(assignableTypes = TutorController.class)
public class TutorControllerAdvice {
    //quando all'interno di TutorController viene lanciata questa eccezione, invoca questo metodo
    @ExceptionHandler(MissingFieldMandatoryException.class)
    public ResponseEntity<TutorError> handleMissingField(MissingFieldMandatoryException e){
        TutorError body = new TutorError(
                LocalDateTime.now(),
                e.getMessage(),
                e.getField()
        );

        return ResponseEntity.badRequest().body(body);
    }
}