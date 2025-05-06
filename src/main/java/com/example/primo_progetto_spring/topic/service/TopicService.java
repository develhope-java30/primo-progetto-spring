package com.example.primo_progetto_spring.topic.service;

import com.example.primo_progetto_spring.classprogram.entity.ClassProgram;
import com.example.primo_progetto_spring.classprogram.repository.ClassProgramRepository;
import com.example.primo_progetto_spring.topic.entity.Topic;
import com.example.primo_progetto_spring.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    ClassProgramRepository classProgramRepository;

    public List<Topic> allTopics(){
        return topicRepository.findAll();
    }

    //aggiunta topic
    public Optional<Topic> addTopic(Topic newTopic){
        return Optional.of(topicRepository.save(newTopic));
    }

    //modifica di un topic
    public Optional<Topic> updateTopic(Long id, Topic topicToUpdate){
        if(!topicRepository.existsById(id)){
            Optional.empty();
        }

        topicToUpdate.setId(id);
        return Optional.of(topicRepository.save(topicToUpdate));
    }

    //cancellazione di un topic
    public void deleteTopicById(Long id){
        topicRepository.deleteById(id);
    }

    //assegnazione topic al programma
    public Optional<ClassProgram> assignTopicToProgram(Long topicId, Long programId){
        Optional<Topic> topic = topicRepository.findById(topicId);
        Optional<ClassProgram> program = classProgramRepository.findById(programId);

        if(topic.isPresent() && program.isPresent()){
            Topic topicToAssign = topic.get();
            ClassProgram programToAssign = program.get();

//            topic.get().setClassProgram(program.get());
            programToAssign.getTopicEntities().add(topicToAssign);

            return Optional.of(classProgramRepository.save(programToAssign));
        }

        return Optional.empty();
    }

    public Page<TopicEntity> getAllTopics (Pageable pageable) {
        return topicRepository.findAll(pageable);

    }

}
