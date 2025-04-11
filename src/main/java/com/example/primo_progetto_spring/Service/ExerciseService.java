package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Exercise;
import com.example.primo_progetto_spring.repository.ExerciseRepository;
import com.example.primo_progetto_spring.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Implementare inserimento di un elemento e lettura di tutti gli elementi presenti
@Service
public class ExerciseService {
    @Autowired private ExerciseRepository exerciseRepository;

    @Autowired
    private StudenteRepository studenteRepository;

    public List<Exercise> allExercise(){
        return exerciseRepository.findAll();
    }

    //il voto al momento della creazione è sempre 0.
    public Optional<Exercise> addExercise(Exercise newExercise){
        newExercise.setVoto(0);
        return Optional.of(exerciseRepository.save(newExercise));
    }
}
