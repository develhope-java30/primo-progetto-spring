package com.example.primo_progetto_spring.team.repository;

import com.example.primo_progetto_spring.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
