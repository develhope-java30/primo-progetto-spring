package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Exercise;
import com.example.primo_progetto_spring.Entity.Studente;
import com.example.primo_progetto_spring.repository.ExerciseRepository;
import com.example.primo_progetto_spring.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

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

    //Esercizi con un determinato voto
    public List<Exercise> findByVoto(Integer voto){
       return exerciseRepository.findByVoto(voto);
    }

    //Esercizi consegnati da un determinato studente
    public Optional<List<Exercise>> findByStudente(Long id){
       Optional<Studente> idFound = studenteRepository.findById(id);

       if(idFound.isEmpty()){
           return Optional.empty();
       }

        return Optional.of(exerciseRepository.findByStudente(idFound.get()));
    }

    public List<Exercise> votoAboveAverage(Long id){
        //cerco lo studente con quell'id e lo associo ad idFound
        Optional<Studente> idFound = studenteRepository.findById(id);
        //nel caso non dovessi trovare lo studente ritorno una lista vuota
        if(idFound.isEmpty()){
            return Collections.emptyList();
        }

        //assegno tutti gli esercizi dello studente con ID ad una lista
        List<Exercise> listOfExerciseByStudentID = exerciseRepository.findByStudente(idFound.get());
        //conto quanti esercizi ha effettuato lo studente
        Long totalExercise = exerciseRepository.countByStudente_Id(id);
        Long sumOfVoti = 0L;

        //ciclo tutti gli esercizi in modo da prendere tutti i voti e sommarli per fare la media
        for(Exercise exercise : listOfExerciseByStudentID){
            sumOfVoti += exercise.getVoto();
        }

        //faccio la media
        Long average = sumOfVoti / totalExercise;

        //creo una lista dove aggiungere tutti gli esercizi che superano la media
        List<Exercise> exercisesOverAverage = new ArrayList<>();
        //ri-ciclo la lista di tutti gli esercizi in modo da confrontarli con la media per in fine aggiungerli alla lista
        for(Exercise exercise : listOfExerciseByStudentID){
            if(exercise.getVoto() > average){
                exercisesOverAverage.add(exercise);
            }
        }
        return exercisesOverAverage;
    }

    //Esercizi il cui voto è superiore a quello preso in media dallo studente.
    public List<Exercise> votoMinorAverage(){
        Long totalExercise = exerciseRepository.count();
        Long sumOfVoti = 0L;

       for(Exercise exercise : exerciseRepository.findAll()){
           sumOfVoti += exercise.getVoto();
       }

       Long average = sumOfVoti / totalExercise;

       List<Exercise> exercisesOverAverage = new ArrayList<>();
       for(Exercise exercise : exerciseRepository.findAll()){
           if(exercise.getVoto() > average){
                exercisesOverAverage.add(exercise);
           }
       }
       return exercisesOverAverage;
    }

}
