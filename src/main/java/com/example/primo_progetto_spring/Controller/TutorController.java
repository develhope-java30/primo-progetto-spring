package com.example.primo_progetto_spring.Controller;

import com.example.primo_progetto_spring.Service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    TutorService tutorService;
}
