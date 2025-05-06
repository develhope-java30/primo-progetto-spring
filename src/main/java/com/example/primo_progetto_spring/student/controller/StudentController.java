package com.example.primo_progetto_spring.student.controller;

import com.example.primo_progetto_spring.student.service.StudentService;
import com.example.primo_progetto_spring.student.entity.Student;
import com.example.primo_progetto_spring.student.component.StudentTestPopulator;
import com.example.primo_progetto_spring.util.PaginationAndSortingRequest;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    StudentService studentService;

    @Autowired
    private StudentTestPopulator studentTestPopulator;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> findAll () {
       return studentService.findAll();
    }

    @GetMapping
    public List<Student> getStudenti(@Nullable @RequestParam String nome) {
            return studentService.getStudenti(nome);
    }

    @GetMapping("/prefisso")
    public List<Student> getNomeStartingWith (@Nullable @RequestParam String prefisso) {
        return studentService.getNomeStartingWith(prefisso);
    }

    @GetMapping("/filter")
    public List<Student> getEtaLessThanEqual (@Nullable @RequestParam Integer etaMinima) {
        return studentService.getEtaGreaterThanEqual(etaMinima);
    }

    @PostMapping
    public ResponseEntity<Student> addStudente(@RequestBody Student student) {
        Optional<Student> studentSaved = studentService.addStudente(student);

        return studentSaved.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/codiceFiscale")
    public ResponseEntity<Student> getStudentePerCodiceFiscale(@RequestParam String codiceFiscale) {
        if (codiceFiscale == null || codiceFiscale.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Student> studenteTrovato = studentService.findStudenteByTaxCode(codiceFiscale);

        if (studenteTrovato.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studenteTrovato.get());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {

        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeByID(@PathVariable Long id) {
        try {
            studentService.removeByID(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudente(@PathVariable Long id, @RequestBody Student updateStudent){
        if(id < 0){
            return ResponseEntity.badRequest().build();
        }

        Optional<Student> studentUpdate = studentService.updateStudent(id, updateStudent);
        if (studentUpdate.isPresent()) {
            return ResponseEntity.ok(studentUpdate.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/con-prefisso")
    public ResponseEntity<Student> trovaStudenteConPrefisso() {

        return studentService.trovaStudenteConPrefisso()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/con-suffisso")
    public ResponseEntity<List<Student>> trovaStudenteConSuffisso(){
        List<Student> studenti = studentService.trovaStudenteConSuffisso();
        return ResponseEntity.ok(studenti);
    }

    @GetMapping("/suffisso-nome")
    public ResponseEntity<List<Student>> suffissoNome(){
        return ResponseEntity.ok(studentService.prefissoNome());
    }

    @PostMapping("/sample")
    @ResponseStatus(HttpStatus.OK)
    public void addSampleStudents() {
        studentTestPopulator.addSampleStudents();
    }

    @GetMapping("/pageable-sorting")
    public ResponseEntity<Page<Student>> getAllStudentPaginated(PaginationAndSortingRequest paginationAndSortingRequest) {
        Pageable pageable = paginationAndSortingRequest.toPageable();
        Page<Student> students = studentService.findAllPaginated(pageable);
        return ResponseEntity.ok(students);
    }
//    @GetMapping("/paginated")
//    public ResponseEntity<Page<Student>> allStudentsPaginated(
//                                               @RequestParam(required = false) Optional<String> sortKey,
//                                               @RequestParam(required = false, defaultValue = "0") int page,
//                                               @RequestParam(required = false, defaultValue = "30") int length,
//                                               @RequestParam(required = false, defaultValue = "true") boolean ascending){
//
//        Sort sorted = sortKey
//                .map(Sort::by)
//                .map((config) -> ascending ? config.ascending() : config.descending())
//                .orElse(Sort.unsorted());
//
//        try{
//            return ResponseEntity.ok(studentService.studentePaginated(sorted, page, length));
//        }catch (PropertyReferenceException e){
//            return ResponseEntity.badRequest().build();
//        }
//
//    }

}
