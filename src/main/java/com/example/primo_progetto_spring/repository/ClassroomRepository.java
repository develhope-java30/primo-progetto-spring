package com.example.primo_progetto_spring.repository;

import com.example.primo_progetto_spring.Entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
