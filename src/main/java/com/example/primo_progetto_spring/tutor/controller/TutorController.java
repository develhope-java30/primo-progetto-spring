package com.example.primo_progetto_spring.tutor.controller;

import com.example.primo_progetto_spring.tutor.service.error.MissingFieldMandatoryException;
import com.example.primo_progetto_spring.tutor.service.TutorService;
import com.example.primo_progetto_spring.tutor.entity.Tutor;
import com.example.primo_progetto_spring.tutor.dto.AddTutorToClassroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutors")
public class TutorController {

    @Autowired
    TutorService tutorService;

    @GetMapping("/all")
    public List<Tutor> getAllTutors () {
        return tutorService.getAllTutors();
    }

    @PostMapping
    public ResponseEntity<Tutor> addTutor(@RequestBody Tutor tutor) {
        return ResponseEntity.ok(tutorService.addTutor(tutor).orElseThrow());
    }

    @PutMapping("/{tutorId}/classrooms")
    public ResponseEntity<Tutor> getTutorToClassroom (@PathVariable Long tutorId, @RequestBody AddTutorToClassroom data) {

        return tutorService.getTutorToClassroom(tutorId, data)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{tutorId}/classrooms")
    public ResponseEntity<Tutor> removeTutorFromClassroom (@PathVariable Long tutorId, @RequestBody AddTutorToClassroom data) {
        return tutorService.removeTutorFromClassroom(tutorId, data)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }
}
