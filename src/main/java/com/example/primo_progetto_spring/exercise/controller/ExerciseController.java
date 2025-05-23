package com.example.primo_progetto_spring.exercise.controller;

import com.example.primo_progetto_spring.exercise.service.ExerciseService;
import com.example.primo_progetto_spring.exercise.entity.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired private ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<Exercise>> allExercise(){
       return ResponseEntity.ok(exerciseService.allExercise());
    }

    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise newExercise){
        Optional<Exercise> exerciseSaved = exerciseService.addExercise(newExercise);

        if(exerciseSaved.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(exerciseSaved.get());
    }

    @PutMapping("/{studentId}/add-exercise/{exerciseId}")
    public ResponseEntity<Exercise> addExerciseToStudent(@PathVariable Long exerciseId,
                                                         @PathVariable Long studentId,
                                                         @RequestParam (required = false) Integer voto){
        return exerciseService.addExerciseToStudent(exerciseId, studentId, voto)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/voto/{voto}")
    public List<Exercise> findByVoto(@PathVariable Integer voto){
        return exerciseService.findByVoto(voto);
    }

    @GetMapping("/studente/{id}")
    public ResponseEntity<List<Exercise>> findByStudente(@PathVariable Long id){
        Optional<List<Exercise>> exerciseList = exerciseService.findByStudente(id);

        if(exerciseList.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(exerciseList.get());
    }

    @GetMapping("/voti-maggiori/{id}")
    public ResponseEntity<List<Exercise>> votoAboveAverage(@PathVariable Long id){
        List<Exercise> votoAbove = exerciseService.votoAboveAverage(id);

        if(votoAbove.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(votoAbove);
    }

    @GetMapping("/voti-minori")
    public List<Exercise> votoMinorAverage(){
        return exerciseService.votoMinorAverage();
    }

}
