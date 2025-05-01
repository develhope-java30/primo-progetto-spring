package com.example.primo_progetto_spring.Classroom.entity;

import com.example.primo_progetto_spring.Coordinator.entity.Coordinator;

import com.example.primo_progetto_spring.Classprogram.entity.ClassProgram;
import com.example.primo_progetto_spring.Student.entity.Student;
import com.example.primo_progetto_spring.Tutor.entity.Tutor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //creo una annotazione one to one per dire che esiste un'entità in relazione con Classroom
    //MappedBy indica chi è il proprietario della relazione
    //CascadeType fa in modo che tutte le operazioni vengono propagate all'interno dell'entità associata / .ALL
    //FetchType fa in modo che Classroom non venga recuperata finché l'altra entità non la recuperi -- fetch = FetchType.LAZY

    //nelle many to one il parametro mappedBy va inserito nella relazione inversa, cioè nella classe dove viene effettuata la
    //OneToMany, in modo da non creare duplicati (righe, o addirittura tabelle).
    @ManyToOne(cascade = CascadeType.ALL)
    private Coordinator coordinator;

    //Relazione One to many
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Student> students;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private ClassProgram classProgram;

    // relazione inversa, proprietà di classrooms definisce la relazione principale
    @ManyToMany(mappedBy = "classrooms")
    @JsonIgnore
    // lista vuota per i tutor associati ad una classroom
    private List<Tutor> tutors;

    private Classroom(){}

    public Classroom(String name){
        this.name = name;
    }

    public ClassProgram getClassProgram() {
        return classProgram;
    }

    public void setClassProgram(ClassProgram classProgram) {
        this.classProgram = classProgram;
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

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudente() {
        return students;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }
}
