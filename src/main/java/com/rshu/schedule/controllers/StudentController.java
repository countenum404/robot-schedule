package com.rshu.schedule.controllers;


import com.rshu.schedule.entities.Student;
import com.rshu.schedule.exceptions.EntityNotFoundException;
import com.rshu.schedule.repos.StudentRepository;
import com.rshu.schedule.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudents(@RequestParam(required = false) String firstname,
                                         @RequestParam(required = false) String lastname,
                                         @RequestParam(required = false) String surname){
        if (firstname == null && lastname == null){
            return new ResponseEntity<Collection<Student>>(studentsService.getStudents(), HttpStatus.OK);
        }
        try {
            Student student = studentsService.findStudent(firstname, lastname, surname);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (EntityNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addStudent(@RequestParam(required = true) String firstname,
                                        @RequestParam(required = true) String lastname,
                                        @RequestParam(required = false) String surname){
        return new ResponseEntity<Boolean>(studentsService.createStudent(firstname, lastname, surname), HttpStatus.CREATED);
    }
}
