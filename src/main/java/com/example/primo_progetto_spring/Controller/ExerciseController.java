package com.example.primo_progetto_spring.Controller;

import com.example.primo_progetto_spring.Entity.Exercise;
import com.example.primo_progetto_spring.Service.ExerciseService;
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
}
