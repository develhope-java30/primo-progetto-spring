package com.example.primo_progetto_spring.Entity;

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
    private Studente studente;

    private String consegna;
    private Integer voto;

    public Exercise() {
    }

    public Exercise(Long id, Studente studente, String consegna, Integer voto) {
        this.id = id;
        this.studente = studente;
        this.consegna = consegna;
        this.voto = voto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
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
