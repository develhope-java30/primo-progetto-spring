package com.example.primo_progetto_spring.Classroom.service;

import com.example.primo_progetto_spring.Classroom.entity.Classroom;
import com.example.primo_progetto_spring.Classroom.repository.ClassroomRepository;
import com.example.primo_progetto_spring.Student.entity.Student;
import com.example.primo_progetto_spring.Student.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    //aggiunto autowired per allocare la classe Studente
    @Autowired
    private StudenteRepository studenteRepository;

    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Optional<Classroom> addStudentToClassroom(Long classroomId, Long studentId) throws Exception {
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);
        Optional<Student> studenteOptional = studenteRepository.findById(studentId);

        if (studenteOptional.isPresent() && classroomOptional.isPresent()) {
            if(classroomOptional.get().getClassProgram() == null){
                throw new Exception("Nessun corso assegnato a questa classe! Impossibile aggiungere lo studente!");
            }

            Student student = studenteOptional.get();
            student.setClassroom(classroomOptional.get());
            studenteRepository.save(student);
            return classroomRepository.findById(classroomId);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Classroom> getClassroomById(Long classroomId) {
        return classroomRepository.findById(classroomId);
    }

    public List<Classroom> allClasses(){
        return classroomRepository.findAll();
    }
}
