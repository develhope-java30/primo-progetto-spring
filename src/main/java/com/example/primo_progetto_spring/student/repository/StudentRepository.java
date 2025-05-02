package com.example.primo_progetto_spring.student.repository;

import com.example.primo_progetto_spring.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    List<Student> findByNome (String nome);

    List<Student> findByNomeStartingWith(String prefisso);

    List<Student> findByDataLessThanEqual(LocalDate dataMinima);

}
