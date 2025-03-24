package com.example.primo_progetto_spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        Studente studente1 = new Studente("Mario", "Rozzi", "2004-11-18", "RZZMRA04S18642Z");
//
//        return studente1.studentInfo();
        return studenti;
    }

    @PostMapping("/studente")
    public void addStudente() {
        studenti.add(new Studente("Mario", "Rozzi", "2004-11-18", "RZZMRA04S18642Z"));
    }

    @GetMapping("/getFirst")
    public Optional<Studente> firstStudent() {
        if (studenti.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.ofNullable(studenti.get(0));
        }
    }
}
