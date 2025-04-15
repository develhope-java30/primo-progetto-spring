package com.example.primo_progetto_spring.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Studente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private String data;
    private String codiceFiscale;

    //relazione Many to one
    @ManyToOne
    @JoinColumn(name = "classroom_Id")
    @JsonIgnore
    private Classroom classroom;

    // relazione uno a molti, uno studente può avere più esercizi assegnati,
    // "student" si riferisce esattamente al nome del campo nella classe Exercise, non la colonna nel database.
    // cascade = quando si elimina uno studente vengono eliminati anche tutti gli esercizi a lui assegnati
    @OneToMany(mappedBy = "studente", cascade = CascadeType.ALL)
    // evita cicli infiniti
    @JsonIgnore
    private List<Exercise> exercises;

    public Studente() {
    }

    public Studente(Long id, String nome, String cognome, String data, String codiceFiscale) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.codiceFiscale = codiceFiscale;
    }

    //getter e setter Exercise
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercise(List<Exercise> exercise) {
        this.exercises = exercise;
    }

    //getter e setter classroom
    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String studentInfo() {
        return (this.nome + "\n" + this.cognome + "\n" + this.data + "\n" + this.codiceFiscale);
    }
}
