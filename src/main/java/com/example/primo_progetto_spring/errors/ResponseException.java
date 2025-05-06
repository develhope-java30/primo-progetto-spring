package com.example.primo_progetto_spring.errors;

//Inserire una classe astratta "ResponseError" che abbia un metodo toErrorDTO(), da implementare nelle eccezioni
//che possono essere ritornate come body di una risposta d'errore. Il metodo deve ritornare i dati dell'eccezione
//all'interno di un ErrorDTO. Per semplificare, per adesso assumiamo che esista un unico ErrorDTO in tutto il sistema

public abstract class ResponseException extends RuntimeException {

    public ResponseException(String message){
        super(message);
    }

    public abstract ErrorResponseDTO toErrorDTO();

}
