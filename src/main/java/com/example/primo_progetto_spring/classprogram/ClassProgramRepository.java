package com.example.primo_progetto_spring.classprogram;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassProgramRepository extends JpaRepository<ClassProgram, Long> {
}
