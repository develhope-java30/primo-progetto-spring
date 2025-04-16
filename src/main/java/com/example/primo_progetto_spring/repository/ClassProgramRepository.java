package com.example.primo_progetto_spring.repository;

import com.example.primo_progetto_spring.Entity.ClassProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassProgramRepository extends JpaRepository<ClassProgram, Long> {
}
