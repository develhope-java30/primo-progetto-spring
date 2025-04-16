package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Tutor;
import com.example.primo_progetto_spring.repository.ClassroomRepository;
import com.example.primo_progetto_spring.repository.StudenteRepository;
import com.example.primo_progetto_spring.repository.TutorRepository;
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
    StudenteRepository studenteRepository;

    public List<Tutor> getAllTutors(){
      return  tutorRepository.findAll();
    }

    public Optional<Tutor> getTutorById (Long id) {
        return tutorRepository.findById(id);
    }

    public Tutor addTutor (Tutor tutor) {
        return tutorRepository.save(tutor);
    }
}
