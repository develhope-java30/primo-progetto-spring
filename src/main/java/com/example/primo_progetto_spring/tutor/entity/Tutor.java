package com.example.primo_progetto_spring.tutor.entity;


import com.example.primo_progetto_spring.classroom.entity.Classroom;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String email;

    @ManyToMany
//    @JoinTable(
//            //nome della tabella di appoggio per la relazione
//            name = "tutor_classroom",
//            // Join sulla colonna classroom_id
//            joinColumns = @JoinColumn(name = "classroom_id"),
//            // riferimento alla chiave primaria di Tutor nel join
//            inverseJoinColumns = @JoinColumn(name = "tutor_id")
//    )
    // lista vuota per le aule associate ad un tutor
    private List<Classroom> classrooms = new ArrayList<>();

    private Tutor() {}

    public Tutor(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}
