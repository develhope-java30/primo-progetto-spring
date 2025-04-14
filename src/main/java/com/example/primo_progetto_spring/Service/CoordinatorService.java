package com.example.primo_progetto_spring.Service;

import com.example.primo_progetto_spring.Entity.Classroom;
import com.example.primo_progetto_spring.Entity.Coordinator;
import com.example.primo_progetto_spring.repository.ClassroomRepository;
import com.example.primo_progetto_spring.repository.CoordinatorRepository;
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
        Optional<Coordinator> coordinatorFounded = coordinatorRepository.findById(coordinatorId);
        Optional<Classroom> classroomFounded = classroomRepository.findById(classroomId);

        if(coordinatorFounded.isPresent() && classroomFounded.isPresent()){
            coordinatorFounded.get().setClassroom(classroomFounded.get());

            coordinatorRepository.save(coordinatorFounded.get());
            return coordinatorFounded;
        }else{
            return Optional.empty();
        }
    }
}
