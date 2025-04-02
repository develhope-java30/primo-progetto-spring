package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Studente;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudenteService {
    private List<Studente> studenti = new ArrayList<>();

    public List<Studente> getStudenti() {
        return studenti;
    }

    public Optional<Studente> addStudente(Studente studente) {
        if (studente.getNome() == null || studente.getCognome() == null) {
            return Optional.empty();
        }

        studenti.add(studente);
        return Optional.of(studente);
    }

    public Optional<Studente> firstStudent() {
        if (studenti.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(studenti.get(0));
        }
    }

    public Optional<Studente> lastStudente() {
        Integer last = studenti.size() - 1;

        if (studenti.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(studenti.get(last));
        }
    }
//    mi prendi un oggetto sutdenti se ce mi ritorni il cf se no

    public Optional<Studente> findStudenteByTaxCode(String codiceFiscaleDaRicercare) {
        for (Studente studente : studenti) {
            String cFStudente = studente.getCodiceFiscale();
            if (cFStudente.equals(codiceFiscaleDaRicercare)) {

                return Optional.of(studente);
            }
        }
        return Optional.empty();

    }

    public void removeByID(int id) {
        try {
            studenti.remove(id);
            System.out.println("ID: " + id + " rimosso");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("ID non valido!");
        }
    }
}
