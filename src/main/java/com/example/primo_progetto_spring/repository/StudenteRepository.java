package com.example.primo_progetto_spring.repository;

import com.example.primo_progetto_spring.Entity.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long>{

    List<Studente> findByNome (String nome);

    List<Studente> findByNomeStartingWith (String prefisso);

    List<Studente> findByDataLessThanEqual(LocalDate dataMinima);
}
