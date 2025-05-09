package com.example.primo_progetto_spring.project.service;

import com.example.primo_progetto_spring.project.entity.Project;
import com.example.primo_progetto_spring.project.repository.ProjectRepository;
import com.example.primo_progetto_spring.tutor.entity.Tutor;
import com.example.primo_progetto_spring.tutor.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public Project updateProjectTutor(Long projectId, Tutor tutor) {
        Project projectOptional = projectRepository.findById(projectId).orElseThrow(()-> new RuntimeException("Progetto non trovato!"));

        projectOptional.setTutor(tutor);
        return projectRepository.save(projectOptional);
    }
}
