package com.example.primo_progetto_spring.tutor.service;


import com.example.primo_progetto_spring.classroom.entity.Classroom;
import com.example.primo_progetto_spring.classroom.repository.ClassroomRepository;
import com.example.primo_progetto_spring.errors.MissingFieldMandatoryException;
import com.example.primo_progetto_spring.student.repository.StudentRepository;
import com.example.primo_progetto_spring.tutor.entity.Tutor;
import com.example.primo_progetto_spring.tutor.repository.TutorRepository;
import com.example.primo_progetto_spring.tutor.dto.AddTutorToClassroom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    StudentRepository studentRepository;

    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    public Optional<Tutor> getTutorById(Long id) {
        return tutorRepository.findById(id);
    }

    public Optional<Tutor> addTutor(Tutor tutor) throws MissingFieldMandatoryException {
        if (tutor.getName() == null || tutor.getName().isEmpty()) {
            throw new MissingFieldMandatoryException("name");
        }

        if(tutor.getSurname() == null || tutor.getSurname().isEmpty()) {
            throw new MissingFieldMandatoryException("surname");
        }

        if(tutor.getEmail() == null || tutor.getEmail().isEmpty()) {
            throw new MissingFieldMandatoryException("email");
        }

        return Optional.of(tutorRepository.save(tutor));
    }

    // Assegnare un tutor ad una classe
    public Optional<Tutor> getTutorToClassroom (Long tutorId, AddTutorToClassroom classroomId) {
        Optional<Tutor> tutorOptional = tutorRepository.findById(tutorId);
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId.getClassroomId());

        if (tutorOptional.isPresent() && classroomOptional.isPresent()) {
            Tutor tutor = tutorOptional.get();
            Classroom classroom = classroomOptional.get();

            tutor.getClassrooms().add(classroom);

            return Optional.of(tutorRepository.save(tutor));
        }

        return Optional.empty();
    }

    // Rimuovere un tutor da una classe
    public Optional<Tutor> removeTutorFromClassroom (Long tutorId, AddTutorToClassroom data) {
        Optional<Tutor> tutorOptional = tutorRepository.findById(tutorId);
        Optional<Classroom> classroomOptional = classroomRepository.findById(data.getClassroomId());

        if (tutorOptional.isPresent() && classroomOptional.isPresent()) {
            Tutor tutor = tutorOptional.get();
            Classroom classroom = classroomOptional.get();

            tutor.getClassrooms().remove(classroom);

            return Optional.of(tutorRepository.save(tutor));
        }
        return Optional.empty();
    }

//    // Elencare i tutor assegnati ad una classe
//    public List<Tutor> getTutorByClassroom (Long classroomId) {
//
//    }
}
