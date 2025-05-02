package com.example.primo_progetto_spring.classroom.repository;

import com.example.primo_progetto_spring.classroom.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
