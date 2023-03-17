package com.rshu.schedule.api.controllers;

import com.rshu.schedule.entities.Teacher;
import com.rshu.schedule.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/teachers")
public class TeachersController {

    @Autowired
    private TeacherService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllTeachers(){
        return new ResponseEntity<Collection<Teacher>>(service.findAllTeachers(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewTeacher(@RequestBody Teacher newTeacher){
        return new ResponseEntity<Boolean>(service.addTeacher(newTeacher), HttpStatus.CREATED);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTeacher(){
        return ResponseEntity.ok("OK");
    }
}
