package com.example.primo_progetto_spring.team.controller;

import com.example.primo_progetto_spring.student.entity.Student;
import com.example.primo_progetto_spring.team.entity.Team;
import com.example.primo_progetto_spring.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/create-team")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {

        if (team.getMembers() == null ||
                team.getClassroom() == null ||
                team.getProject() == null) {
            return ResponseEntity.badRequest().build();
        }

        Team newTeam = teamService.createTeam(team.getMembers(), team.getClassroom().getId(), team.getProject().getId());

        return ResponseEntity.ok(newTeam);
    }

    @PutMapping("/{teamId}/update-members")
    public ResponseEntity<Team> updateTeamMembers(@PathVariable Long teamId, @RequestBody List<Student> newMembers) {

        Team updatedTeam = teamService.updateTeamMembers(teamId, newMembers);

        return ResponseEntity.ok(updatedTeam);
    }
}
