package com.example.primo_progetto_spring.coordinator.service;

import com.example.primo_progetto_spring.classroom.entity.Classroom;
import com.example.primo_progetto_spring.classroom.repository.ClassroomRepository;


import com.example.primo_progetto_spring.coordinator.entity.Coordinator;

import com.example.primo_progetto_spring.coordinator.repository.CoordinatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordinatorService {
    @Autowired private CoordinatorRepository coordinatorRepository;
    @Autowired private ClassroomRepository classroomRepository;

    public List<Coordinator> allCoordinator(){
        return coordinatorRepository.findAll();
    }

    public Optional<Coordinator> addCoordinator(Coordinator newCoordinator){
        if(newCoordinator.getName().isEmpty()){
            return Optional.empty();
        }

        coordinatorRepository.save(newCoordinator);
        return Optional.of(newCoordinator);
    }

    public Optional<Coordinator> assignCoordinatorToClass(Long coordinatorId, Long classroomId){
        Optional<Coordinator> coordinatorFound = coordinatorRepository.findById(coordinatorId);
        Optional<Classroom> classroomFound = classroomRepository.findById(classroomId);

        if(coordinatorFound.isPresent() && classroomFound.isPresent()){
            classroomFound.get().setId(classroomId);
            classroomFound.get().setCoordinator(coordinatorFound.get());

            classroomRepository.save(classroomFound.get());
            return Optional.of(classroomFound.get().getCoordinator());
        }
        return Optional.empty();
    }
}
