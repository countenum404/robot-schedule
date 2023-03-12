package com.rshu.schedule.controllers;

import com.rshu.schedule.entities.Student;
import com.rshu.schedule.entities.StudyGroup;
import com.rshu.schedule.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGroups(){
        return new ResponseEntity<Collection<StudyGroup>>(studentsService.getGroups(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addGroup(@RequestParam String groupName){
        return new ResponseEntity<Boolean>(studentsService.createGroup(groupName), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addStudentAtGroup(@RequestBody Student student){
        System.out.println(student);
        return new ResponseEntity<Boolean>(studentsService.mapStudentAndGroup(student), HttpStatus.ACCEPTED);
    }
}
