package com.example.primo_progetto_spring.Student.service;

import com.example.primo_progetto_spring.Student.repository.StudenteRepository;
import com.example.primo_progetto_spring.Student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudenteService {
    @Autowired
    private StudenteRepository studenteRepository;

    private List<Student> studenti = new ArrayList<>();

    public List<Student> findAll () {
       return studenteRepository.findAll();
    }

    public List<Student> getStudenti(String nome) {
        return studenteRepository.findByNome(nome);
    }

    public List<Student> getNomeStartingWith (String prefisso) {

        return studenteRepository.findByNomeStartingWith(prefisso);
    }

    public List<Student> getEtaGreaterThanEqual (Integer etaMinima) {
        LocalDate now = LocalDate.now();
        LocalDate dataMax = now.minusYears(etaMinima);

        return studenteRepository.findByDataLessThanEqual(dataMax);

    }

    public Optional<Student> addStudente(Student student) {
        if (student.getNome() == null ||
                student.getCognome() == null ||
                student.getData() == null) {
            return Optional.empty();
        }

        return Optional.of(studenteRepository.save(student));
    }

    public Optional<Student> findStudenteByTaxCode(String codiceFiscaleDaRicercare) {
        for (Student student : studenti) {
            String cFStudente = student.getCodiceFiscale();
            if (cFStudente.equals(codiceFiscaleDaRicercare)) {

                return Optional.of(student);
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

    public Optional<Student> findById(Long id) {

        return studenteRepository.findById(id);
    }

    public Optional<Student> updateStudent(Long id, Student student){
      if(!studenteRepository.existsById(id)){
          return Optional.empty();
      }

      student.setId(id);
      return Optional.of(studenteRepository.save(student));
    }

    public Optional<Student> trovaStudenteConPrefisso(){
        List<Student> allStudents = studenteRepository.findAll();

        //confronta gli studenti
        for (int i = 0; i < allStudents.size(); i++) {
            Student student1 = allStudents.get(i);
            String nome1 = student1.getNome();

            for (int j = 0; j < allStudents.size(); j++) {

                Student student2 = allStudents.get(j);
                if (i == j) {
                    continue;
                }

                String nome2 = allStudents.get(j).getNome();

                //nome 2 è prefisso di nome1
                if (nome2.startsWith(nome1)){
                    return Optional.of(student2);
                }

            }
        }
        return Optional.empty();
    }

    // Implementare un endpoint che ritorni gli studenti il cui nome è suffisso del cognome di un altro studente
    public List<Student> trovaStudenteConSuffisso(){
        List<Student> allStudent = studenteRepository.findAll();
        List<Student> studentiTrovati = new ArrayList<>();

        for (int i = 0; i < allStudent.size() ; i++) {
            Student student1 = allStudent.get(i);
            String nome1 = student1.getNome().toLowerCase();

            for (int j = 0; j < allStudent.size() ; j++) {
                Student student2 = allStudent.get(j);
                String cognome2 = student2.getCognome().toLowerCase();

                if (i == j){
                    continue;
                }

                if (cognome2.endsWith(nome1)){
                    studentiTrovati.add(student2);
                }
            }
        }
        return studentiTrovati;
    }

    //Implementare un endpoint che ritorni gli studenti il cui cognome è prefisso del nome di un altro studente
    public List<Student> prefissoNome(){
        List<Student> prefissoList = studenteRepository.findAll();
        List<Student> studentiAll = new ArrayList<>();

        for(int i = 0; i < prefissoList.size(); i++){
            Student student1 = prefissoList.get(i);
            String cognome1 = student1.getCognome().toLowerCase();

            for(int y = 0; y < prefissoList.size(); y++){
                Student student2 = prefissoList.get(y);
                String nome2 = student2.getNome().toLowerCase();

                if(i == y){
                    continue;
                }

                if(nome2.startsWith(cognome1)){
                    studentiAll.add(student1);
                }
            }
        }
        return studentiAll;
    }

    //
    public Page<Student> studentePaginated(Sort sorted, int page, int length){
        Pageable pagination = PageRequest.of(page, length, sorted);

        return studenteRepository.findAll(pagination);
    }

}