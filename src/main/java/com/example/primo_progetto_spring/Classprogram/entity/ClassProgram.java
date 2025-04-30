package com.example.primo_progetto_spring.Classprogram.entity;

import com.example.primo_progetto_spring.Classroom.entity.Classroom;
import com.example.primo_progetto_spring.Topic.entity.TopicEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

//Implementare una nuova entity ClassProgram, che rappresenta il programma di un corso, strutturato in varie sezioni ed argomenti.
//È possibile (e consigliato) utilizzare varie classi Entity per implementare la struttura delle tabelle.

@Entity
public class ClassProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private LocalDate duration;

    @OneToMany(mappedBy = "classProgram", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Classroom> classrooms;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<TopicEntity> topicEntities;

    private ClassProgram(){}

    public ClassProgram(String name, String type, LocalDate duration) {
        this.name = name;
        this.type = type;
        this.duration = duration;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDuration() {
        return duration;
    }

    public void setDuration(LocalDate duration) {
        this.duration = duration;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<TopicEntity> getTopicEntities() {
        return topicEntities;
    }

    public void setTopicEntities(List<TopicEntity> topicEntities) {
        this.topicEntities = topicEntities;
    }

}
