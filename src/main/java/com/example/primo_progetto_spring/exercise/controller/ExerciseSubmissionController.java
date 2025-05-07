package com.example.primo_progetto_spring.exercise.controller;

import com.example.primo_progetto_spring.exercise.service.ExerciseSubmissionService;
import com.example.primo_progetto_spring.exercise.entity.ExerciseSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise")
public class ExerciseSubmissionController {
    @Autowired private ExerciseSubmissionService exerciseService;

    @GetMapping
    public ResponseEntity<List<ExerciseSubmission>> allExercise(){
       return ResponseEntity.ok(exerciseService.allExercise());
    }

    @PostMapping
    public ResponseEntity<ExerciseSubmission> addExercise(@RequestBody ExerciseSubmission newExercise){
        Optional<ExerciseSubmission> exerciseSaved = exerciseService.addExercise(newExercise);

        if(exerciseSaved.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(exerciseSaved.get());
    }

    @PutMapping("/{studentId}/add-exercise/{exerciseId}")
    public ResponseEntity<ExerciseSubmission> addExerciseToStudent(@PathVariable Long exerciseId,
                                                                   @PathVariable Long studentId,
                                                                   @PathVariable Long exDefinitionId,
                                                                   @RequestParam (required = false) Integer voto){
        return exerciseService.addExerciseToStudent(exerciseId, studentId, exDefinitionId, voto)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/voto/{voto}")
    public List<ExerciseSubmission> findByVoto(@PathVariable Integer voto){
        return exerciseService.findByVoto(voto);
    }

    @GetMapping("/studente/{id}")
    public ResponseEntity<List<ExerciseSubmission>> findByStudente(@PathVariable Long id){
        Optional<List<ExerciseSubmission>> exerciseList = exerciseService.findByStudente(id);

        if(exerciseList.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(exerciseList.get());
    }

    @GetMapping("/voti-maggiori/{id}")
    public ResponseEntity<List<ExerciseSubmission>> votoAboveAverage(@PathVariable Long id){
        List<ExerciseSubmission> votoAbove = exerciseService.votoAboveAverage(id);

        if(votoAbove.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(votoAbove);
    }

    @GetMapping("/voti-minori")
    public List<ExerciseSubmission> votoMinorAverage(){
        return exerciseService.votoMinorAverage();
    }

}
