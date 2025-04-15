package com.example.primo_progetto_spring.Controller;

import com.example.primo_progetto_spring.Entity.Studente;
import com.example.primo_progetto_spring.Service.StudenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/studenti")
public class StudentController {

    private final StudenteService studenteService;

    public StudentController(StudenteService studenteService) {
        this.studenteService = studenteService;
    }

    @GetMapping
    public List<Studente> getStudenti() {
        return studenteService.getStudenti();
    }


    @PostMapping
    public ResponseEntity<Studente> addStudente(@RequestBody Studente studente) {
        Optional<Studente> studentSaved = studenteService.addStudente(studente);

        return studentSaved.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/codiceFiscale")
    public ResponseEntity<Studente> getStudentePerCodiceFiscale(@RequestParam String codiceFiscale) {
        if (codiceFiscale == null || codiceFiscale.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Studente> studenteTrovato = studenteService.findStudenteByTaxCode(codiceFiscale);

        if (studenteTrovato.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studenteTrovato.get());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Studente> findById(@PathVariable Long id) {

        return studenteService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeByID(@PathVariable Long id) {
        try {
            studenteService.removeByID(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Studente> updateStudente(@PathVariable Long id, @RequestBody Studente updateStudente){
        if(id < 0){
            return ResponseEntity.badRequest().build();
        }

        Optional<Studente> studentUpdate = studenteService.updateStudent(id, updateStudente);
        if (studentUpdate.isPresent()) {
            return ResponseEntity.ok(studentUpdate.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/con-prefisso")
    public ResponseEntity<Studente> trovaStudenteConPrefisso() {

        return studenteService.trovaStudenteConPrefisso()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/con-suffisso")
    public ResponseEntity<List<Studente>> trovaStudenteConSuffisso(){
        List<Studente> studenti = studenteService.trovaStudenteConSuffisso();
        return ResponseEntity.ok(studenti);
    }

    @GetMapping("/age-less-30")
    public ResponseEntity<List<Studente>> ageLess30(){
        Optional<List<Studente>> age = studenteService.ageLess30();

        if(age.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(age.get());
    }

    @GetMapping("/by-age")
    public ResponseEntity<List<Studente>> studentiConXEta(@RequestParam int age){
        List<Studente> ageOfStudent = studenteService.studentiConXEta(age);
        return ResponseEntity.ok(ageOfStudent);
    }

    @GetMapping("/suffisso-nome")
    public ResponseEntity<List<Studente>> suffissoNome(){
        return ResponseEntity.ok(studenteService.prefissoNome());
    }

}