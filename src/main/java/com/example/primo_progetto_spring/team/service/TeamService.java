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

    // create Team, Ogni team dev'essere associato ad un progetto,
    // Ogni team dev'essere associato ad una classe,
//    I membri del team devono appartenere tutti alla stessa classe

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

        // verifica che nel team ci siano dalle 2 alle 5 persone.
        boolean isValid = members.size() >= 2 && members.size() <= 5;

        Team team = new Team(members, classroomOptional.get(), projectOptional.get(), isValid);
        return teamRepository.save(team);
    }

    //È possibile avere un team in database che non è ancora valido, ma va mantenuto
    // un campo che indica se il team è valido o meno,
    // aggiornandolo man mano che cambiano i membri, apporta anche queste modifiche
    public Optional<Team> updateTeamMembers(Long teamId, List<Student> newMembers) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);

        if (teamOptional.isEmpty()) {
            return Optional.empty();
        }

        for (Student member : newMembers) {
            if (!member.getClassroom().equals(teamOptional.get().getClassroom())) {
                throw new RuntimeException("Tutti i membri devono appartenere alla stessa classe!");
            }
        }

        boolean isValid = newMembers.size() >= 2 && newMembers.size() <= 5;

        teamOptional.get().setMembers(newMembers);
        teamOptional.get().setValid(isValid);

        return Optional.of(teamRepository.save(teamOptional.get()));
    }

}
