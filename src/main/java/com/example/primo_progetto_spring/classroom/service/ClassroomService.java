package com.example.primo_progetto_spring.classroom.service;

import com.example.primo_progetto_spring.classroom.dto.AddStudentToClassroom;
import com.example.primo_progetto_spring.classroom.entity.Classroom;
import com.example.primo_progetto_spring.classroom.repository.ClassroomRepository;
import com.example.primo_progetto_spring.errors.NotAssignedCourseException;
import com.example.primo_progetto_spring.student.entity.Student;
import com.example.primo_progetto_spring.student.repository.StudentRepository;
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
    private StudentRepository studentRepository;

    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Optional<Classroom> addStudentToClassroom(Long classroomId, AddStudentToClassroom data) {
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);
        Optional<Student> studenteOptional = studentRepository.findById(data.getStudentId());

        if (studenteOptional.isPresent() && classroomOptional.isPresent()) {
            if(classroomOptional.get().getClassProgram() == null){
                throw new NotAssignedCourseException(classroomId);
            }

            Student student = studenteOptional.get();
            student.setClassroom(classroomOptional.get());
            studentRepository.save(student);
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
