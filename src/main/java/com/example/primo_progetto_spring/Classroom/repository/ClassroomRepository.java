package com.example.primo_progetto_spring.Classroom.repository;

import com.example.primo_progetto_spring.Classroom.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
