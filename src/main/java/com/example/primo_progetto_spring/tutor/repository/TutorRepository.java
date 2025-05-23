package com.example.primo_progetto_spring.tutor.repository;

import com.example.primo_progetto_spring.tutor.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    List<Tutor> findByName (String name);
}
