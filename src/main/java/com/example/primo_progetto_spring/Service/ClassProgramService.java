package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.ClassProgram;
import com.example.primo_progetto_spring.repository.ClassProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassProgramService {
    @Autowired ClassProgramRepository classProgramRepository;

    public List<ClassProgram> allPrograms(){
       return classProgramRepository.findAll();
    }

    //Creare un nuovo programma
    public Optional<ClassProgram> addProgram(ClassProgram newProgram){
        return Optional.of(classProgramRepository.save(newProgram));
    }

    //Modificare un programma esistente
    public Optional<ClassProgram> updateProgram(Long id, ClassProgram programToUpdate){
        if(classProgramRepository.existsById(id)){
            return Optional.empty();
        }

        programToUpdate.setId(id);
        return Optional.of(classProgramRepository.save(programToUpdate));
    }

    public void deleteProgramById(Long id){
        classProgramRepository.deleteById(id);
    }

    //Assegnare un programma ad una classe
    //      Quest'operazione si può effettuare solo se alla classe non è ancora stato aggiunto alcuno studente.
    //      Modificare l'endpoint che aggiunge uno studente ad una classe verificando che alla classe sia già stato assegnato un corso, ritornando altrimenti un errore.

}
