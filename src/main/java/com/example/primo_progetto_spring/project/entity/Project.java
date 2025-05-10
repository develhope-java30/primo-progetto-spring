package com.example.primo_progetto_spring.project.entity;

import com.example.primo_progetto_spring.classroom.entity.Classroom;
import com.example.primo_progetto_spring.tutor.entity.Tutor;
import jakarta.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private Tutor tutor;

    @ManyToOne
    private Classroom classroom;

    private Project() {}

    public Project(Long id, String name, String description, Tutor tutor, Classroom classroom) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tutor = tutor;
        this.classroom = classroom;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
