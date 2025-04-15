package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Exercise;
import com.example.primo_progetto_spring.Entity.Studente;
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

    public Optional<Exercise> addExerciseToStudent(Long exerciseId, Long studentId, Integer voto){
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        Optional<Studente> studenteOptional = studenteRepository.findById(studentId);

        if (exerciseOptional.isPresent() && studenteOptional.isPresent()) {
            Studente studente = studenteOptional.get();
            Exercise exercise = exerciseOptional.get();

            exercise.setStudente(studente);

            // imposta il voto
            if (voto != null) {
                exercise.setVoto(voto);
            }

            studente.getExercises().add(exerciseRepository.save(exercise));
            studenteRepository.save(studente);

            return Optional.of(exerciseRepository.save(exercise));
        }
        return Optional.empty();
    }
}
