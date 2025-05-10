package com.example.primo_progetto_spring.topic.controller;

import com.example.primo_progetto_spring.classprogram.entity.ClassProgram;
import com.example.primo_progetto_spring.topic.service.TopicService;

import com.example.primo_progetto_spring.util.PaginationAndSortingRequest;
import com.example.primo_progetto_spring.topic.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping
    public List<Topic> allTopics() {
        return topicService.allTopics();
    }

    @PostMapping
    public ResponseEntity<Topic> addTopic(@RequestBody Topic newTopic) {
        Optional<Topic> addedTopic = topicService.addTopic(newTopic);

        return ResponseEntity.ok(addedTopic.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topicToUpdate) {
        Optional<Topic> updatedTopic = topicService.updateTopic(id, topicToUpdate);

        if (updatedTopic.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(updatedTopic.get());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTopic(Long id) {
        topicService.deleteTopicById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{topicId}/program/{programId}")
    public ResponseEntity<ClassProgram> assignTopicToProgram(@PathVariable Long topicId, @PathVariable Long programId) {
        Optional<ClassProgram> assignedTopic = topicService.assignTopicToProgram(topicId, programId);

        if (assignedTopic.isEmpty()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(assignedTopic.get());
    }

    @GetMapping("/pageable-sorting")
    public ResponseEntity<Page<Topic>> getAllTopics(PaginationAndSortingRequest paginationAndSortingRequest) {
        Pageable pageable = paginationAndSortingRequest.toPageable();
        Page<Topic> topics = topicService.getAllTopics(pageable);

        return ResponseEntity.ok(topics);
    }

}
