package com.example.primo_progetto_spring.exercise.service;

import com.example.primo_progetto_spring.exercise.entity.ExerciseDefinition;
import com.example.primo_progetto_spring.exercise.repository.ExerciseDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseDefinitionService {
    @Autowired
    private ExerciseDefinitionRepository exerciseDefinitionRepository;

    public List<ExerciseDefinition> exerciseDefinitionList(){
        return exerciseDefinitionRepository.findAll();
    }

    public ExerciseDefinition addExerciseDefinition(ExerciseDefinition newExerciseDefinition){
        return exerciseDefinitionRepository.save(newExerciseDefinition);
    }


}
