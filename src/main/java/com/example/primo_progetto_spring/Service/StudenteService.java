package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Studente;
import com.example.primo_progetto_spring.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudenteService {
    @Autowired
    private StudenteRepository studenteRepository;

    private List<Studente> studenti = new ArrayList<>();

    public List<Studente> findAll () {
       return studenteRepository.findAll();
    }

    public List<Studente> getStudenti(String nome) {
        return studenteRepository.findByNome(nome);
    }

    public List<Studente> getNomeStartingWith (String prefisso) {

        return studenteRepository.findByNomeStartingWith(prefisso);
    }

    public List<Studente> getEtaGreaterThanEqual (Integer etaMinima) {
        LocalDate now = LocalDate.now();
        LocalDate dataMax = now.minusYears(etaMinima);

        return studenteRepository.findByDataLessThanEqual(dataMax);

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

    //Implementare un endpoint che ritorni gli studenti il cui cognome è prefisso del nome di un altro studente
    public List<Studente> prefissoNome(){
        List<Studente> prefissoList = studenteRepository.findAll();
        List<Studente> studentiAll = new ArrayList<>();

        for(int i = 0; i < prefissoList.size(); i++){
            Studente studente1 = prefissoList.get(i);
            String cognome1 = studente1.getCognome().toLowerCase();

            for(int y = 0; y < prefissoList.size(); y++){
                Studente studente2 = prefissoList.get(y);
                String nome2 = studente2.getNome().toLowerCase();

                if(i == y){
                    continue;
                }

                if(nome2.startsWith(cognome1)){
                    studentiAll.add(studente1);
                }
            }
        }
        return studentiAll;
    }

}