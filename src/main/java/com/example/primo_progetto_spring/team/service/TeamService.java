package com.example.primo_progetto_spring.team.service;

import com.example.primo_progetto_spring.classroom.entity.Classroom;
import com.example.primo_progetto_spring.classroom.repository.ClassroomRepository;
import com.example.primo_progetto_spring.project.entity.Project;
import com.example.primo_progetto_spring.project.repository.ProjectRepository;
import com.example.primo_progetto_spring.student.entity.Student;
import com.example.primo_progetto_spring.team.entity.Team;
import com.example.primo_progetto_spring.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Team createTeam(List<Student> members, Long classroomId, Long projectId) {
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (classroomOptional.isEmpty() || projectOptional.isEmpty()) {
            throw new RuntimeException("Classe o Progetto non trovato");
        }

        for (Student member : members) {
            if (!member.getClassroom().equals(classroomOptional.get())) {
                throw new RuntimeException("Tutti i membri devono appartenere alla stessa classe");
            }
        }

        boolean isValid = members.size() >= 2 && members.size() <= 5;

        Team team = new Team(members, classroomOptional.get(), projectOptional.get(), isValid);
        return teamRepository.save(team);
    }

    public Team updateTeamMembers(Long teamId, List<Student> newMembers) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team non trovato!"));


        for (Student member : newMembers) {
            if (!member.getClassroom().equals(team.getClassroom())) {
                throw new RuntimeException("Tutti i membri devono appartenere alla stessa classe!");
            }
        }

        boolean isValid = newMembers.size() >= 2 && newMembers.size() <= 5;

        team.setMembers(newMembers);
        team.setValid(isValid);

        return teamRepository.save(team);
    }

}
