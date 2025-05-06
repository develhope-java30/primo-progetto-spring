package com.example.primo_progetto_spring.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//https://www.bezkoder.com/spring-boot-restcontrolleradvice/
//https://github.com/bezkoder/spring-boot-restcontrolleradvice

//Generalizzare il ControllerAdvice per fare in modo che venga eseguito su tutte le eccezioni
//che implementano l'interfaccia ResponseError (da verificare se si può fare, non ne sono sicuro).

//assignableTypes ci permette di prendere l'eccezioni di solo questa classe per non incappare in eccezioni di altri controller.
@RestControllerAdvice
public class GeneralControllerAdvice {
    //quando all'interno di TutorController viene lanciata questa eccezione, invoca questo metodo
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingField(ResponseException e){
        return ResponseEntity.badRequest().body(e.toErrorDTO());
    }
}