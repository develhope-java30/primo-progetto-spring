package com.example.primo_progetto_spring.coordinator.controller;

import com.example.primo_progetto_spring.coordinator.entity.Coordinator;
import com.example.primo_progetto_spring.coordinator.service.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordinator")
public class CoordinatorController {
    @Autowired
    CoordinatorService coordinatorService;

    @GetMapping
    public List<Coordinator> allCoordinator(){
        return coordinatorService.allCoordinator();
    }

    @PostMapping
    public ResponseEntity<Coordinator> addCoordinator(@RequestBody Coordinator coordinator){
        if(coordinator.getName().isEmpty()){
           return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(coordinatorService.addCoordinator(coordinator).get());
    }

    @PostMapping("/{coordinatorId}/class/{classId}")
    public ResponseEntity<Coordinator> AssignCoordinatorToClass(@PathVariable Long coordinatorId, @PathVariable Long classId){
       return coordinatorService.assignCoordinatorToClass(coordinatorId, classId)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
