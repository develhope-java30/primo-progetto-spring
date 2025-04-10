package com.example.primo_progetto_spring.repository;

import com.example.primo_progetto_spring.Entity.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {}
