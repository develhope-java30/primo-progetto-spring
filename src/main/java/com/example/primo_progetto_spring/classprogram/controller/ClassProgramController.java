package com.example.primo_progetto_spring.classprogram.controller;

import com.example.primo_progetto_spring.classprogram.service.ClassProgramService;
import com.example.primo_progetto_spring.classprogram.entity.ClassProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/program")
public class ClassProgramController {
    @Autowired
    ClassProgramService classProgramService;

    @GetMapping
    public List<ClassProgram> allPrograms(){
        return classProgramService.allPrograms();
    }

    @PostMapping
    public ResponseEntity<ClassProgram> addProgram(@RequestBody ClassProgram newProgram){
        Optional<ClassProgram> newProgramSaved = classProgramService.addProgram(newProgram);

        return ResponseEntity.ok(newProgramSaved.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassProgram> updateProgram(@PathVariable Long id, @RequestBody ClassProgram programToUpdate){
        Optional<ClassProgram> updatedProgramSaved = classProgramService.updateProgram(id, programToUpdate);

        if(updatedProgramSaved.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProgramSaved.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id){
        classProgramService.deleteProgramById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{programId}/classroom/{classId}")
    public ResponseEntity<ClassProgram> assignProgramToClass(@PathVariable Long programId, @PathVariable Long classId){
        Optional<ClassProgram> assignedProgram = classProgramService.assignProgramToClassroom(programId, classId);

        if(assignedProgram.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(assignedProgram.get());
    }

}
