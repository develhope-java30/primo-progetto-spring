package com.example.primo_progetto_spring.exercise.entity;

import com.example.primo_progetto_spring.student.entity.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

//Aggiungere una nuova entità "Exercise", definita da id, id dello studente, consegna (stringa) e voto (intero).
@Entity
public class ExerciseSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //relazione molti a uno, uno studente può avere più esercizi assegnati
    @ManyToOne
    //Join sull'Id dello studente
    @JoinColumn(name = "student_Id")
    private Student student;

    private boolean consegna;
    private Integer voto;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    private ExerciseDefinition definition;

    public ExerciseSubmission() {}

    public ExerciseSubmission(Long id, Student student, boolean consegna, Integer voto, ExerciseDefinition definition) {
        this.id = id;
        this.student = student;
        this.consegna = consegna;
        this.voto = voto;
        this.definition = definition;
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

    public boolean getConsegna() {
        return consegna;
    }

    public void setConsegna(boolean consegna) {
        this.consegna = consegna;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public ExerciseDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(ExerciseDefinition definition) {
        this.definition = definition;
    }
}
