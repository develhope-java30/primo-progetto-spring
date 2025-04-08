package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Studente;
import com.example.primo_progetto_spring.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    //Implementare un endpoint che ritorni gli studenti la cui età è minore di 30
    public Optional<List<Studente>> ageLess30(){
        List<Studente> age = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        LocalDate dateOfStudent = null;

        for(Studente studente : studenteRepository.findAll()){
            dateOfStudent = LocalDate.parse(studente.getData());

            if(localDate.getYear() - dateOfStudent.getYear() < 30){
                age.add(studente);
            }
        }

        if(age.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(age);
    }
}
