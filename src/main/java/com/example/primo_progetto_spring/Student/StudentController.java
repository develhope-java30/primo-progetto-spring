package com.example.primo_progetto_spring.Student;

import com.example.primo_progetto_spring.component.StudentiTestPopulator;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/studenti")
public class StudentController {

    @Autowired
    StudenteService studenteService;

    @Autowired
    private StudentiTestPopulator studentiTestPopulator;

    public StudentController(StudenteService studenteService) {
        this.studenteService = studenteService;
    }

    @GetMapping("/all")
    public List<Studente> findAll () {
       return studenteService.findAll();
    }

    @GetMapping
    public List<Studente> getStudenti(@Nullable @RequestParam String nome) {
            return studenteService.getStudenti(nome);
    }

    @GetMapping("/prefisso")
    public List<Studente> getNomeStartingWith (@Nullable @RequestParam String prefisso) {
        return studenteService.getNomeStartingWith(prefisso);
    }

    @GetMapping("/filter")
    public List<Studente> getEtaLessThanEqual (@Nullable @RequestParam Integer etaMinima) {
        return studenteService.getEtaGreaterThanEqual(etaMinima);
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

    @GetMapping("/suffisso-nome")
    public ResponseEntity<List<Studente>> suffissoNome(){
        return ResponseEntity.ok(studenteService.prefissoNome());
    }

    @PostMapping("/sample")
    @ResponseStatus(HttpStatus.OK)
    public void addSampleStudents() {
        studentiTestPopulator.addSampleStudents();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Studente>> allStudentsPaginated(
                                               @RequestParam(required = false) Optional<String> sortKey,
                                               @RequestParam(required = false, defaultValue = "0") int page,
                                               @RequestParam(required = false, defaultValue = "30") int length,
                                               @RequestParam(required = false, defaultValue = "true") boolean ascending){

        Sort sorted = sortKey
                .map(Sort::by)
                .map((config) -> ascending ? config.ascending() : config.descending())
                .orElse(Sort.unsorted());

        try{
            return ResponseEntity.ok(studenteService.studentePaginated(sorted, page, length));
        }catch (PropertyReferenceException e){
            return ResponseEntity.badRequest().build();
        }

    }

}
