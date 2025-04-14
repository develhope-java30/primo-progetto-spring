package com.example.primo_progetto_spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //OneToOne stabilisce che ogni istanza di Classroom è associata ad un'altra entità
    @OneToOne
    //JoinColumn specifica che c'è una relazione nel DB tramite una colonna, presente nella colonna ID di Classroom
    @JoinColumn(name = "classroom_id")
    //JsonIgnore blocca la serializzazione quando un oggetto viene convertito in JSON
    //@JsonIgnore
    //questo campo rappresenta il riferimento all'istanza Classroom
    private Classroom classroom;

    private Coordinator(){}

    public Coordinator(Long id, String name, Classroom classroom){
        this.id = id;
        this.name = name;
        this.classroom = classroom;
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

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

}
