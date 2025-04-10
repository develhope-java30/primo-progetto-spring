package com.example.primo_progetto_spring.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //creo una annotazione one to one per dire che esiste un'entità in relazione con Classroom
    //MappedBy indica chi è il proprietario della relazione
    //CascadeType fa in modo che tutte le operazioni vengono propagate all'interno dell'entità associata
    //FetchType fa in modo che Classroom non venga recuperata finché l'altra entità non la recuperi
    @OneToOne(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Coordinator coordinator;

    //Relazione One to many
    @OneToMany (mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Studente> students = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Studente> getStudente() {
        return students;
    }

    public void setStudenteId(List<Studente> studente) {
        this.students = studente;
    }

}
