package com.example.primo_progetto_spring.coordinator.repository;

import com.example.primo_progetto_spring.coordinator.entity.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {}
