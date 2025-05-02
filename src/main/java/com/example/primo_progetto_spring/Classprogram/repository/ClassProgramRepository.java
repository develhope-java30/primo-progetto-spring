package com.example.primo_progetto_spring.Classprogram.repository;

import com.example.primo_progetto_spring.Classprogram.entity.ClassProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassProgramRepository extends JpaRepository<ClassProgram, Long> {
}
