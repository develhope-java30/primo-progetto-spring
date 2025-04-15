package com.example.primo_progetto_spring.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //creo una annotazione one to one per dire che esiste un'entità in relazione con Classroom
    //MappedBy indica chi è il proprietario della relazione
    //CascadeType fa in modo che tutte le operazioni vengono propagate all'interno dell'entità associata
    //FetchType fa in modo che Classroom non venga recuperata finché l'altra entità non la recuperi -- fetch = FetchType.LAZY
    @ManyToOne(cascade = CascadeType.ALL)
    private Coordinator coordinator;

    //Relazione One to many
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Studente> students = new ArrayList<>();

    private Classroom(){}

    public Classroom(String name){
        this.name = name;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
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

    public void setStudents(List<Studente> students) {
        this.students = students;
    }

    public List<Studente> getStudente() {
        return students;
    }

}
