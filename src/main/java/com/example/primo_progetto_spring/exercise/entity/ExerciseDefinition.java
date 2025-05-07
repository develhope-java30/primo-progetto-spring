package com.example.primo_progetto_spring.exercise.entity;

import com.example.primo_progetto_spring.topic.entity.Topic;
import jakarta.persistence.*;

@Entity
public class ExerciseDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String type;

    @OneToMany
    private Topic topic;

    private ExerciseDefinition(){}

    public ExerciseDefinition(String description, String type, Topic topic){
        this.description = description;
        this.type = type;
        this.topic = topic;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
