package com.rshu.schedule.user;


import com.rshu.schedule.user.User;
import com.rshu.schedule.exceptions.EntityNotFoundException;
import com.rshu.schedule.services.StudentsAndGroupService;
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
    private StudentsAndGroupService studentsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudents(@RequestParam(required = false) String firstname,
                                         @RequestParam(required = false) String lastname,
                                         @RequestParam(required = false) String surname){
        if (firstname == null && lastname == null){
            return new ResponseEntity<Collection<User>>(studentsService.getStudents(), HttpStatus.OK);
        }
        try {
            User user = studentsService.findStudent(firstname, lastname, surname);
            return new ResponseEntity<User>(user, HttpStatus.OK);
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

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteStudent(@RequestBody User user){
        try {
            return new ResponseEntity<Boolean>(studentsService.deleteStudent(user), HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
