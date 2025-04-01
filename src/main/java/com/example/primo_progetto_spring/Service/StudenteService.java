package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Studente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudenteService {
    private List<Studente> studenti = new ArrayList<>();

    public List<Studente> getStudenti(){
        return studenti;
    }
}
