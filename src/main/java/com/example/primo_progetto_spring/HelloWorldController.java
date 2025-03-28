package com.example.primo_progetto_spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;


@RestController
@RequestMapping("/test")
public class HelloWorldController {
    private List<Studente> studenti;

    public HelloWorldController(ArrayList<Studente> studenti) {
        this.studenti = studenti;
    }

    public List<Studente> getStudenti() {
        return studenti;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/studente")
    public List<Studente> studenti() {
        return studenti;
    }

    //Modify
    @PostMapping("/studente")
    public ResponseEntity<String> addStudente(@RequestBody Studente studente) {
        if(studente.getNome() == null || studente.getCognome() == null){
           return ResponseEntity.badRequest().body("Nome e Cognome sono campi obbligatori");
        }

        studenti.add(studente);
        int savedObjectId = studenti.size() -1;


        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedObjectId).toUri())
                .body(studente);
    }

    @GetMapping("/getFirst")
    public Optional<Studente> firstStudent() {
        if (studenti.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(studenti.get(0));
        }
    }

    @GetMapping("/getLastStudente")
    public Optional<Studente> LastStudente() {
        Integer last = studenti.size() - 1;

        if (studenti.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(studenti.get(last));
        }
    }

    @GetMapping("/saluta")
    public String saluta(@RequestParam String nome) {
        return "Ciao " + nome;
    }

    @GetMapping("/utenti/{id}")
    public String dettaglioUtente(@PathVariable Long id) {
        return "Dettaglio utente: " + id;
    }

    @GetMapping("/calcola")
    public Integer calcolaNumeri(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/studenti/{id}")
    public ResponseEntity<Studente> idOfStudent(@PathVariable int id) {
        if (id < 0){
            return ResponseEntity.badRequest().build();
        }

        try {
            Studente result = studenti.get(id);
            return ResponseEntity.ok(result);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/codiceFiscale")
    public Optional<Studente> getStudentePerCodiceFiscale(@RequestParam String codiceFiscale) {
        if (codiceFiscale == null || codiceFiscale.isEmpty()) {
            return Optional.empty();
        }

        for (Studente studente : studenti) {
            if (studente.getCodiceFiscale().equals(codiceFiscale)) {
                return Optional.of(studente);
            }
        }
        return Optional.empty();
    }

    //Modify
    @DeleteMapping("/studenti")
    public ResponseEntity<String> deleteAllStudent() {
        studenti.clear();
        return ResponseEntity.ok("Lista clearata");
    }

    //Modify
    @DeleteMapping("/studenti/{id}")
    public ResponseEntity<String> removeByID(@PathVariable int id) {
        try {
            studenti.remove(id);
            return ResponseEntity.ok("ID: " + id + " rimosso");
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.badRequest().body("ID non valido!");
        }
    }

    @PutMapping("/studenti/{id}")
    public ResponseEntity<Studente> updateStudente(@PathVariable int id, @RequestBody Studente updateStudente){
        if(id < 0){
            return ResponseEntity.badRequest().build();
        }

        try{
            Studente result = studenti.get(id);
            studenti.set(id, updateStudente);
            return ResponseEntity.ok(updateStudente);
        } catch (NullPointerException e){
           return ResponseEntity.notFound().build();
        }
    }
}
