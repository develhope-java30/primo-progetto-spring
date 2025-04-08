package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Studente;
import com.example.primo_progetto_spring.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudenteService {
    @Autowired
    private StudenteRepository studenteRepository;

    private List<Studente> studenti = new ArrayList<>();

    public List<Studente> getStudenti() {
        List<Studente> allStudents = studenteRepository.findAll();
        return allStudents;
    }

    public Optional<Studente> addStudente(Studente studente) {
        if (studente.getNome() == null || studente.getCognome() == null) {
            return Optional.empty();
        }

        return Optional.of(studenteRepository.save(studente));
    }

    public Optional<Studente> findStudenteByTaxCode(String codiceFiscaleDaRicercare) {
        for (Studente studente : studenti) {
            String cFStudente = studente.getCodiceFiscale();
            if (cFStudente.equals(codiceFiscaleDaRicercare)) {

                return Optional.of(studente);
            }
        }
        return Optional.empty();

    }

    public void removeByID(Long id) {
        try {
            studenteRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ID non valido!");
        }
    }

    public Optional<Studente> findById(Long id) {
        if (id < 0) {
            return Optional.empty();
        }

        return studenteRepository.findById(id);
    }

    public Optional<Studente> updateStudent(Long id, Studente studente){
      if(!studenteRepository.existsById(id)){
          return Optional.empty();
      }

      studente.setId(id);
      return Optional.of(studenteRepository.save(studente));
    }

    public Optional<Studente> trovaStudenteConPrefisso(){
        List<Studente> allStudents = studenteRepository.findAll();

        //confronta gli studenti
        for (int i = 0; i < allStudents.size(); i++) {
            Studente studente1 = allStudents.get(i);
            String nome1 = studente1.getNome();

            for (int j = 0; j < allStudents.size(); j++) {

                Studente studente2 = allStudents.get(j);
                if (i == j) {
                    continue;
                }

                String nome2 = allStudents.get(j).getNome();

                //nome 2 è prefisso di nome1
                if (nome2.startsWith(nome1)){
                    return Optional.of(studente2);
                }

            }
        }
        return Optional.empty();
    }

    // Implementare un endpoint che ritorni gli studenti il cui nome è suffisso del cognome di un altro studente
    public List<Studente> trovaStudenteConSuffisso(){
        List<Studente> allStudent = studenteRepository.findAll();
        List<Studente> studentiTrovati = new ArrayList<>();

        for (int i = 0; i < allStudent.size() ; i++) {
            Studente studente1 = allStudent.get(i);
            String nome1 = studente1.getNome().toLowerCase();

            for (int j = 0; j < allStudent.size() ; j++) {
                Studente studente2 = allStudent.get(j);
                String cognome2 = studente2.getCognome().toLowerCase();

                if (i == j){
                    continue;
                }

                if (cognome2.endsWith(nome1)){
                    studentiTrovati.add(studente2);
                }
            }
        }
        return studentiTrovati;
    }
}
