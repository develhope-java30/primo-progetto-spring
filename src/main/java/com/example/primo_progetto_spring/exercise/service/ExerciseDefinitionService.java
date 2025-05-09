package com.example.primo_progetto_spring.exercise.service;

import com.example.primo_progetto_spring.exercise.entity.ExerciseDefinition;
import com.example.primo_progetto_spring.exercise.entity.ExerciseSubmission;
import com.example.primo_progetto_spring.exercise.repository.ExerciseDefinitionRepository;
import com.example.primo_progetto_spring.topic.entity.Topic;
import com.example.primo_progetto_spring.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseDefinitionService {
    @Autowired
    private ExerciseDefinitionRepository exerciseDefinitionRepository;

    @Autowired
    private TopicRepository topicRepository;

    public List<ExerciseDefinition> exerciseDefinitionList(){
        return exerciseDefinitionRepository.findAll();
    }

    public ExerciseDefinition addExerciseDefinition(ExerciseDefinition newExerciseDefinition){
        return exerciseDefinitionRepository.save(newExerciseDefinition);
    }

    public Optional<ExerciseDefinition> assignDefinitionToTopic(Long definitionId, Long topicId){
        Optional<ExerciseDefinition> definition = exerciseDefinitionRepository.findById(definitionId);
        Optional<Topic> topic = topicRepository.findById(topicId);

        if(definition.isPresent() && topic.isPresent()){
            ExerciseDefinition foundDefinition = definition.get();
            Topic foundTopic = topic.get();

            foundDefinition.setTopic(foundTopic);

            return Optional.of(exerciseDefinitionRepository.save(foundDefinition));
        }

        return Optional.empty();
    }

}
