package com.example.primo_progetto_spring.exercise.repository;

import com.example.primo_progetto_spring.exercise.entity.ExerciseSubmission;
import com.example.primo_progetto_spring.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseSubmissionRepository extends JpaRepository<ExerciseSubmission, Long> {

    List<ExerciseSubmission> findByVoto(Integer voto);

    List<ExerciseSubmission> findByStudent(Student student);

    Long countByStudent_Id(Long id);

}
