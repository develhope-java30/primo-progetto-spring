package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Studente;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudenteService {
    private List<Studente> studenti = new ArrayList<>();

    public List<Studente> getStudenti(){
        return studenti;
    }

    public Optional<Studente> addStudente(@RequestBody Studente studente) {
        if(studente.getNome() == null || studente.getCognome() == null){
            return Optional.empty();
        }

        studenti.add(studente);
//        int savedObjectId = studenti.size() -1;

        return Optional.of(studente);
    }
}
