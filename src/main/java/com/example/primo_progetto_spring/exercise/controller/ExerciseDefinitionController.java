package com.example.primo_progetto_spring.exercise.controller;

import com.example.primo_progetto_spring.exercise.entity.ExerciseDefinition;
import com.example.primo_progetto_spring.exercise.service.ExerciseDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise-definition")
public class ExerciseDefinitionController {
    @Autowired
    private ExerciseDefinitionService exerciseDefinitionService;

    @GetMapping
    public List<ExerciseDefinition> allExerciseDefinition(){
        return exerciseDefinitionService.exerciseDefinitionList();
    }

    @PostMapping
    public ResponseEntity<ExerciseDefinition> addExerciseDefinition(@RequestBody ExerciseDefinition newExerciseDefinition){
        return ResponseEntity.ok(exerciseDefinitionService.addExerciseDefinition(newExerciseDefinition));
    }

    @PutMapping("/{id}/{topicId}")
    public ResponseEntity<ExerciseDefinition> assignTopic(@PathVariable Long id, @PathVariable Long topicId){
        Optional<ExerciseDefinition> assignedTopicToDefinition = exerciseDefinitionService.assignTopicToDefinition(id, topicId);

        return assignedTopicToDefinition
                .map(ResponseEntity::ok)
                .orElseGet(ExerciseDefinitionController::emptyNotFound);
    }

    public static <T> ResponseEntity<T> emptyNotFound(){
        return ResponseEntity.notFound().build();
    }
}
