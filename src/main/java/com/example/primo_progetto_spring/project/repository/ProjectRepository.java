package com.example.primo_progetto_spring.project.repository;

import com.example.primo_progetto_spring.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
