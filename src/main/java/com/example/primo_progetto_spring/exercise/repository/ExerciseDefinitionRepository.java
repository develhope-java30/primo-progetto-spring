package com.example.primo_progetto_spring.exercise.repository;

import com.example.primo_progetto_spring.exercise.entity.ExerciseDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseDefinitionRepository extends JpaRepository<ExerciseDefinition, Long> {
}
