package com.example.primo_progetto_spring.exercise.service;

import com.example.primo_progetto_spring.exercise.entity.ExerciseDefinition;
import com.example.primo_progetto_spring.exercise.entity.ExerciseSubmission;
import com.example.primo_progetto_spring.exercise.repository.ExerciseDefinitionRepository;
import com.example.primo_progetto_spring.exercise.repository.ExerciseSubmissionRepository;
import com.example.primo_progetto_spring.student.entity.Student;
import com.example.primo_progetto_spring.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//Implementare inserimento di un elemento e lettura di tutti gli elementi presenti
@Service
public class ExerciseSubmissionService {
    @Autowired
    private ExerciseSubmissionRepository exerciseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExerciseDefinitionRepository exerciseDefinitionRepository;

    public List<ExerciseSubmission> allExercise(){
        return exerciseRepository.findAll();
    }

    //il voto al momento della creazione è sempre 0.
    public Optional<ExerciseSubmission> addExercise(ExerciseSubmission newExercise){
        newExercise.setVoto(0);
        return Optional.of(exerciseRepository.save(newExercise));
    }

    public Optional<ExerciseSubmission> addExerciseToStudent(Long exerciseId, Long studentId, Long exsubmissionId, Integer voto){
        Optional<ExerciseSubmission> exerciseOptional = exerciseRepository.findById(exerciseId);
        Optional<Student> studenteOptional = studentRepository.findById(studentId);
        Optional<ExerciseDefinition> exerciseDefinition = exerciseDefinitionRepository.findById(exsubmissionId);

        if (exerciseOptional.isPresent() && exerciseDefinition.isPresent() && studenteOptional.isPresent()) {
            Student student = studenteOptional.get();
            ExerciseSubmission exercise = exerciseOptional.get();
            ExerciseDefinition exerciseDef = exerciseDefinition.get();

            exercise.setStudente(student);

            // imposta il voto
            if (voto != null) {
                exercise.setVoto(voto);
            }

            student.getExercises().add(exerciseRepository.save(exercise));
            studentRepository.save(student);

            exerciseDefinitionRepository.save(exerciseDef);

            return Optional.of(exerciseRepository.save(exercise));
        }
        return Optional.empty();
    }

    //Esercizi con un determinato voto
    public List<ExerciseSubmission> findByVoto(Integer voto){
       return exerciseRepository.findByVoto(voto);
    }

    //Esercizi consegnati da un determinato studente
    public Optional<List<ExerciseSubmission>> findByStudente(Long id){
       Optional<Student> idFound = studentRepository.findById(id);

       if(idFound.isEmpty()){
           return Optional.empty();
       }

        return Optional.of(exerciseRepository.findByStudent(idFound.get()));
    }

    //Esercizi il cui voto è superiore a quello preso in media dallo studente.
    public List<ExerciseSubmission> votoAboveAverage(Long id){
        //cerco lo studente con quell'id e lo associo ad idFound
        Optional<Student> idFound = studentRepository.findById(id);
        //nel caso non dovessi trovare lo studente ritorno una lista vuota
        if(idFound.isEmpty()){
            return Collections.emptyList();
        }

        //assegno tutti gli esercizi dello studente con ID ad una lista
        List<ExerciseSubmission> listOfExerciseByStudentID = exerciseRepository.findByStudent(idFound.get());
        //conto quanti esercizi ha effettuato lo studente
        Long totalExercise = exerciseRepository.countByStudent_Id(id);
        Long sumOfVoti = 0L;

        //ciclo tutti gli esercizi in modo da prendere tutti i voti e sommarli per fare la media
        for(ExerciseSubmission exercise : listOfExerciseByStudentID){
            sumOfVoti += exercise.getVoto();
        }

        //faccio la media
        Long average = sumOfVoti / totalExercise;

        //creo una lista dove aggiungere tutti gli esercizi che superano la media
        List<ExerciseSubmission> exercisesOverAverage = new ArrayList<>();
        //ri-ciclo la lista di tutti gli esercizi in modo da confrontarli con la media per in fine aggiungerli alla lista
        for(ExerciseSubmission exercise : listOfExerciseByStudentID){
            if(exercise.getVoto() > average){
                exercisesOverAverage.add(exercise);
            }
        }
        return exercisesOverAverage;
    }

    //Esercizi il cui voto è inferiore a quello preso in media da tutti gli studenti.
    public List<ExerciseSubmission> votoMinorAverage(){
        Long totalExercise = exerciseRepository.count();
        Long sumOfVoti = 0L;

       for(ExerciseSubmission exercise : exerciseRepository.findAll()){
           sumOfVoti += exercise.getVoto();
       }

       Long average = sumOfVoti / totalExercise;

       List<ExerciseSubmission> exercisesOverAverage = new ArrayList<>();
       for(ExerciseSubmission exercise : exerciseRepository.findAll()){
           if(exercise.getVoto() < average){
                exercisesOverAverage.add(exercise);
           }
       }
       return exercisesOverAverage;
    }

}
