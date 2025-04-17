package com.example.primo_progetto_spring.Controller;

import com.example.primo_progetto_spring.Entity.Tutor;
import com.example.primo_progetto_spring.Service.TutorService;
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

    @PutMapping("/{tutorId}/add-tutor-to-classroom/{classroomId}")
    public ResponseEntity<Tutor> getTutorToClassroom (@PathVariable Long tutorId, @PathVariable Long classroomId) {

        return tutorService.getTutorToClassroom(tutorId, classroomId)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }
}
