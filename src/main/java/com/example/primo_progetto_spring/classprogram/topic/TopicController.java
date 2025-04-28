package com.example.primo_progetto_spring.classprogram.topic;

import com.example.primo_progetto_spring.classprogram.ClassProgram;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TopicEntity> allTopics() {
        return topicService.allTopics();
    }

    @PostMapping
    public ResponseEntity<TopicEntity> addTopic(@RequestBody TopicEntity newTopic) {
        Optional<TopicEntity> addedTopic = topicService.addTopic(newTopic);

        return ResponseEntity.ok(addedTopic.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicEntity> updateTopic(@PathVariable Long id, @RequestBody TopicEntity topicToUpdate) {
        Optional<TopicEntity> updatedTopic = topicService.updateTopic(id, topicToUpdate);

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
    public ResponseEntity<List<TopicEntity>> getAllTopics(@RequestParam(required = false) String name,
                                          @RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam(defaultValue = "asc") boolean ascending) {

        Sort sort;
        if (ascending) {
            sort = Sort.by("name");
        } else {
            sort = Sort.by("name").descending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        List<TopicEntity> topics = topicService.getAllTopics(pageable);
        return ResponseEntity.ok(topics);
    }

}
