package com.example.primo_progetto_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    //JoinColumn specifica che c'è una relazione nel DB tramite una colonna, presente nella colonna ID di Classroom
//  @JoinColumn(name = "classroomId")
    //JsonIgnore blocca la serializzazione quando un oggetto viene convertito in JSON
    //@JsonIgnore
    //questo campo rappresenta il riferimento all'istanza Classroom
    private List<Classroom> classrooms;

    private Coordinator(){}

    public Coordinator(Long id, String name, List<Classroom> classrooms){
        this.id = id;
        this.name = name;
        this.classrooms = classrooms;
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

}
