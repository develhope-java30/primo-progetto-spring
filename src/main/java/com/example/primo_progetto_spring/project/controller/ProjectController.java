package com.example.primo_progetto_spring.project.controller;

import com.example.primo_progetto_spring.project.entity.Project;
import com.example.primo_progetto_spring.project.service.ProjectService;
import com.example.primo_progetto_spring.tutor.entity.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PutMapping("/{projectId}/tutor")
    public ResponseEntity<Project> updateProjectTutor(@PathVariable Long projectId, @RequestBody Tutor tutor) {
        Project projectUpdate = projectService.updateProjectTutor(projectId, tutor);

        return ResponseEntity.ok(projectUpdate);
    }
}
