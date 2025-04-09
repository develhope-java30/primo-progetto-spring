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
    private Long studentId;
    private String consegna;
    private Integer voto;

    public Exercise(){}

    public Exercise(Long id, Long studentId, String consegna, Integer voto){
        this.id = id;
        this.studentId = studentId;
        this.consegna = consegna;
        this.voto = voto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long student_id) {
        this.studentId = student_id;
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
