package com.example.primo_progetto_spring.exercise.entity;

import com.example.primo_progetto_spring.topic.entity.Topic;
import jakarta.persistence.*;

import java.util.List;

//Ogni Topic deve avere un set di esercizi da consegnare (ExerciseDefinition);

//Rinominare l'entity Exercise in ExerciseSubmission; X

// Ogni submission dev'essere legata non solo all'utente che ha consegnato l'esercizio, ma anche all'ExerciseDefinition seguente;

//Aggiungere gli endpoint necessari o aggiornare quelli esistenti per eseguire le seguenti operazioni:

//Aggiungere una nuova ExerciseDefinition e assegnarla a un topic;

//Consegnare l'esercizio associandolo a una definizione;

//Recuperare, per ogni studente, quali sono i topic per i quali deve ancora consegnare esercizi;

//Recuperare, per ogni studente, quali sono i topic per cui ha consegnato tutti gli esercizi;


@Entity
public class ExerciseDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String type;

    @ManyToMany
    private List<Topic> topics;

    private ExerciseDefinition(){}

    public ExerciseDefinition(String description, String type, List<Topic> topics){
        this.description = description;
        this.type = type;
        this.topics = topics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Topic> getTopic() {
        return topics;
    }

    public void setTopic(List<Topic> topic) {
        this.topics = topics;
    }

}
