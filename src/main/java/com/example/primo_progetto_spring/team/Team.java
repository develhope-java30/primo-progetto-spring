package com.example.primo_progetto_spring.team;

import com.example.primo_progetto_spring.classroom.entity.Classroom;
import com.example.primo_progetto_spring.project.Project;
import com.example.primo_progetto_spring.student.entity.Student;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
//    @JoinTable(
//            name = "team_student",
//            joinColumns = @JoinColumn(name = "team_id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id")
//    )
    private List<Student> members = new ArrayList<>();

    @ManyToOne
    private Classroom classroom;

    @OneToOne(cascade = CascadeType.ALL)
    private Project project;

    private boolean valid;

    private Team() {}

    public Team(List<Student> members, Classroom classroom, Project project, boolean valid) {
        this.members = members;
        this.classroom = classroom;
        this.project = project;
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
