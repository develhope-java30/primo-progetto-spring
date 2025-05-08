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

    public Optional<Project> updateProjectTutor(Long projectId, Long tutorId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Optional<Tutor> tutorOptional = tutorRepository.findById(tutorId);

        if (projectOptional.isEmpty() || tutorOptional.isEmpty()) {
            return Optional.empty();
        }

        projectOptional.get().setTutor(tutorOptional.get());
        return Optional.of(projectRepository.save(projectOptional.get()));
    }
}
