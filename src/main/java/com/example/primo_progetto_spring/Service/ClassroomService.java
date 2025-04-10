package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Classroom;
import com.example.primo_progetto_spring.Entity.Studente;
import com.example.primo_progetto_spring.repository.ClassroomRepository;
import com.example.primo_progetto_spring.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    //aggiunto autowired per allocare la classe Studente
    @Autowired
    private StudenteRepository studenteRepository;

    public Classroom createClassroom() {
        Classroom classroom = new Classroom();
        return classroomRepository.save(classroom);
    }

    public Optional<Classroom> addStudentToClassroom(Long classroomId, Long studentId) {
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);
        Optional<Studente> studenteOptional = studenteRepository.findById(studentId);

        if (studenteOptional.isPresent() && classroomOptional.isPresent()) {
            Studente studente = studenteOptional.get();
            studente.setClassroom(classroomOptional.get());
            studenteRepository.save(studente);
            return classroomRepository.findById(classroomId);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Classroom> getClassroomById(Long classroomId) {
        return classroomRepository.findById(classroomId);
    }
}
