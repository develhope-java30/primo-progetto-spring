package com.example.primo_progetto_spring.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relazione One to many
    @OneToMany (mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Studente> students = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Studente> getStudente() {
        return students;
    }

    public void setStudenteId(List<Studente> studente) {
        this.students = studente;
    }
}
