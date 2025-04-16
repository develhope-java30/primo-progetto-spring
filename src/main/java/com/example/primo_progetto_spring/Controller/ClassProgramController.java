package com.example.primo_progetto_spring.Controller;

import com.example.primo_progetto_spring.Entity.ClassProgram;
import com.example.primo_progetto_spring.Service.ClassProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/program")
public class ClassProgramController {
    @Autowired ClassProgramService classProgramService;

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
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(updatedProgramSaved.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id){
        classProgramService.deleteProgramById(id);
        return ResponseEntity.noContent().build();
    }
    
}
