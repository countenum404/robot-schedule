package com.rshu.schedule.controllers;


import com.rshu.schedule.entities.Student;
import com.rshu.schedule.repos.StudentRepository;
import com.rshu.schedule.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public ResponseEntity<?> hello(){
        return new ResponseEntity<Collection<Student>>(studentsService.getStudents(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestParam(required = true) String firstName,
                                        @RequestParam(required = true) String secondName,
                                        @RequestParam(required = false) String surname){
        return new ResponseEntity<Boolean>(studentsService.createStudent(firstName, secondName, surname), HttpStatus.CREATED);
    }

}
