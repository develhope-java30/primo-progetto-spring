package com.example.primo_progetto_spring.classroom.controller;

import com.example.primo_progetto_spring.classroom.service.ClassroomService;
import com.example.primo_progetto_spring.classroom.entity.Classroom;
import com.example.primo_progetto_spring.errors.ErrorResponseDTO;
import com.example.primo_progetto_spring.errors.NotAssignedCourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping
    public Classroom createClassroom (@RequestBody Classroom classroom){
        return classroomService.createClassroom(classroom);
    }

    @PutMapping("/{classroomId}/{studentId}")
    //wildcard generica di Java, Il punto interrogativo rappresenta un tipo generico sconosciuto e accetta più tipi di oggetti.
    public ResponseEntity<?> addStudentToClassroom(@PathVariable Long classroomId, @PathVariable Long studentId){
        try {
            return classroomService.addStudentToClassroom(classroomId, studentId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (NotAssignedCourseException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @GetMapping("/{classroomId}")
    public ResponseEntity<Classroom> getClassroomById (@PathVariable Long classroomId) {
        return classroomService.getClassroomById(classroomId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Classroom> allClasses(){
        return classroomService.allClasses();
    }
}
