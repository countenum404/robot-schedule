package com.rshu.schedule.controllers;


import com.rshu.schedule.dto.ScheduleDto;
import com.rshu.schedule.entities.ScheduleRecord;
import com.rshu.schedule.entities.StudyGroup;
import com.rshu.schedule.entities.Subject;
import com.rshu.schedule.entities.Teacher;
import com.rshu.schedule.repos.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFullSchedule(){
        return new ResponseEntity<List<ScheduleRecord>>(scheduleRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleDto scheduleRecord){
        ScheduleRecord record = new ScheduleRecord();
        record.getSubjects().add(scheduleRecord.getSubject());
        record.getTeachers().add(scheduleRecord.getTeacher());
        record.getStudyGroup().add(scheduleRecord.getGroup());
        return new ResponseEntity<Boolean>(HttpStatus.CREATED);
    }

}
