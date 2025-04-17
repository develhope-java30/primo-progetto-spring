package com.example.primo_progetto_spring.classprogram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired TopicRepository topicRepository;
    @Autowired ClassProgramRepository classProgramRepository;

    public List<TopicEntity> allTopics(){
        return topicRepository.findAll();
    }

    public Optional<TopicEntity> addTopic(TopicEntity newTopic){
        return Optional.of(topicRepository.save(newTopic));
    }

    public void deleteTopicById(Long id){
        topicRepository.deleteById(id);
    }

    public Optional<TopicEntity> assignTopicToProgram(Long topicId, Long programId){
        Optional<TopicEntity> topic = topicRepository.findById(topicId);
        Optional<ClassProgram> program = classProgramRepository.findById(programId);

        if(topic.isPresent() && program.isPresent()){
            topic.get().setClassProgram(program.get());
            return Optional.of(topicRepository.save(topic.get()));
        }

        return Optional.empty();
    }

}
