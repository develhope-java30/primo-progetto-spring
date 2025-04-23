package com.example.primo_progetto_spring.Controller;

import com.example.primo_progetto_spring.Entity.Tutor;
import com.example.primo_progetto_spring.Service.TutorService;
import com.example.primo_progetto_spring.dto.AddTutorToClassroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Tutor> addTutor (@RequestBody Tutor tutor) {
        return tutorService.addTutor(tutor);
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
