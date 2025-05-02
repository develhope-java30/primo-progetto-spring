package com.example.primo_progetto_spring.Student.component;

import com.example.primo_progetto_spring.Student.entity.Student;
import com.example.primo_progetto_spring.Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class StudentTestPopulator {

    @Autowired
    StudentService studentService;

    public void addSampleStudents () {
        List<Student> studenti = new ArrayList<>();

        // Array di esempi per generare dati realistici
        String[] nomi = {"Mario", "Luigi", "Giulia", "Francesca", "Carlo", "Anna", "Marco", "Sara", "Giorgio", "Luca", "Gino", "Vincent", "Fabiana", "Ciro", "Assunta", "Elvira"};
        String[] cognomi = {"Rozzi", "Bianchi", "Verdi", "Neri", "Ferrari", "Esposito", "Russo", "Conti", "Marino", "Ricci", "Santonicola", "Cupero", "Nguruge"};

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            String nome = nomi[random.nextInt(nomi.length)];
            String cognome = cognomi[random.nextInt(cognomi.length)];
            LocalDate dataDiNascita = LocalDate.of(
                    random.nextInt(30) + 1980,  // Genera anno tra 1980 e 2010
                    random.nextInt(12) + 1,     // Genera mese tra 1 e 12
                    random.nextInt(28) + 1      // Genera giorno tra 1 e 28
            );

            // Creazione dello studente
            Student student = new Student(
                    null, // l'ID sarà generato automaticamente
                    nome,
                    cognome,
                    dataDiNascita,
                    null // codiceFiscale è opzionale e quindi lasciato null
            );
            studentService.addStudente(student);
        }
    }
}
