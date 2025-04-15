package com.example.primo_progetto_spring.repository;

import com.example.primo_progetto_spring.Entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByVoto(Integer voto);

}
