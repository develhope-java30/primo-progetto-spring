package com.example.primo_progetto_spring.Controller;

import com.example.primo_progetto_spring.Entity.Classroom;
import com.example.primo_progetto_spring.Service.ClassroomService;
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

    @PutMapping("/{classroomId}/add-student/{studentId}")
    public ResponseEntity<Classroom> addStudentToClassroom(@PathVariable Long classroomId, @PathVariable Long studentId){
        return classroomService.addStudentToClassroom(classroomId, studentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
