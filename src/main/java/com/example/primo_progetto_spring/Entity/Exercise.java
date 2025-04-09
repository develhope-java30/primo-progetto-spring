package com.example.primo_progetto_spring.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Aggiungere una nuova entità "Exercise", definita da id, id dello studente, consegna (stringa) e voto (intero).
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long student_id;
    private String consegna;
    private Integer voto;

    public Exercise(){}

    public Exercise(Long id, Long student_id, String consegna, Integer voto){
        this.id = id;
        this.student_id = student_id;
        this.consegna = consegna;
        this.voto = voto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
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
