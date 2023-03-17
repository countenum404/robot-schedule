package com.rshu.schedule.api.controllers;

import com.rshu.schedule.dto.GroupStudentDto;
import com.rshu.schedule.entities.StudyGroup;
import com.rshu.schedule.exceptions.EntityNotFoundException;
import com.rshu.schedule.services.StudentsAndGroupService;
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
    private StudentsAndGroupService studentsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGroups(){
        return new ResponseEntity<Collection<StudyGroup>>(studentsService.getGroups(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addGroup(@RequestParam String group){
        return new ResponseEntity<Boolean>(studentsService.createGroup(group), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addStudentAtGroup(@RequestBody GroupStudentDto studentToGroup){
        try {
            Boolean isMapped = studentsService.mapStudentAndGroup(studentToGroup);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
