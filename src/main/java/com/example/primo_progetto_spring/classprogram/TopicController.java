package com.example.primo_progetto_spring.classprogram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired TopicService topicService;

    @GetMapping
    public List<TopicEntity> allTopics(){
        return topicService.allTopics();
    }

    @PostMapping
    public ResponseEntity<TopicEntity> addTopic(@RequestBody TopicEntity newTopic){
        Optional<TopicEntity> addedTopic = topicService.addTopic(newTopic);

        return ResponseEntity.ok(addedTopic.get());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTopic(Long id){
        topicService.deleteTopicById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{topicId}/program/{programId}")
    public ResponseEntity<TopicEntity> assignTopicToProgram(@PathVariable Long topicId, @PathVariable Long programId){
        Optional<TopicEntity> assignedTopic = topicService.assignTopicToProgram(topicId, programId);

        if(assignedTopic.isEmpty()){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(assignedTopic.get());
    }

}
