package com.example.primo_progetto_spring.exercise.entity;

import com.example.primo_progetto_spring.student.entity.Student;
import jakarta.persistence.*;

//Aggiungere una nuova entità "Exercise", definita da id, id dello studente, consegna (stringa) e voto (intero).
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //relazione molti a uno, uno studente può avere più esercizi assegnati
    @ManyToOne
    //Join sull'Id dello studente
    @JoinColumn(name = "student_Id")
    private Student student;

    private String consegna;
    private Integer voto;

    public Exercise() {
    }

    public Exercise(Long id, Student student, String consegna, Integer voto) {
        this.id = id;
        this.student = student;
        this.consegna = consegna;
        this.voto = voto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudente() {
        return student;
    }

    public void setStudente(Student student) {
        this.student = student;
    }

    public String getConsegna() {
        return consegna;
    }

    public void setConsegna(String consegna) {
        this.consegna = consegna;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }
}
