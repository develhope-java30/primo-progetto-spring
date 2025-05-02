package com.example.primo_progetto_spring.classprogram.service;

import com.example.primo_progetto_spring.classprogram.entity.ClassProgram;
import com.example.primo_progetto_spring.classprogram.repository.ClassProgramRepository;
import com.example.primo_progetto_spring.classroom.entity.Classroom;
import com.example.primo_progetto_spring.classroom.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassProgramService {
    @Autowired
    ClassProgramRepository classProgramRepository;
    @Autowired ClassroomRepository classroomRepository;

    public List<ClassProgram> allPrograms(){
       return classProgramRepository.findAll();
    }

    //Creare un nuovo programma
    public Optional<ClassProgram> addProgram(ClassProgram newProgram){
        return Optional.of(classProgramRepository.save(newProgram));
    }

    //Modificare un programma esistente
    public Optional<ClassProgram> updateProgram(Long id, ClassProgram programToUpdate){
        if(!classProgramRepository.existsById(id)){
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
    public Optional<ClassProgram> assignProgramToClassroom(Long programId, Long classroomId){
        Optional<ClassProgram> classProgram = classProgramRepository.findById(programId);
        Optional<Classroom> classroom = classroomRepository.findById(classroomId);

        if(classProgram.isPresent() && classroom.isPresent() && classroom.get().getStudente().isEmpty()){
                classroom.get().setId(classroomId);
                classroom.get().setClassProgram(classProgram.get());

                classroomRepository.save(classroom.get());
                return Optional.of(classroom.get().getClassProgram());
        }

        return Optional.empty();
    }

}
