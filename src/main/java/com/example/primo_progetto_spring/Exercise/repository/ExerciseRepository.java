package com.example.primo_progetto_spring.Exercise.repository;

import com.example.primo_progetto_spring.Exercise.entity.Exercise;
import com.example.primo_progetto_spring.Student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByVoto(Integer voto);

    List<Exercise> findByStudent(Student student);

    Long countByStudent_Id(Long id);

}
