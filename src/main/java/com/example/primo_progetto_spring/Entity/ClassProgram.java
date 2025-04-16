package com.example.primo_progetto_spring.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

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

    private ClassProgram(){}

    public ClassProgram(String name, String type, LocalDate duration) {
        this.name = name;
        this.type = type;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

}
