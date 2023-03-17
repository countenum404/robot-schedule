package com.rshu.schedule.api.controllers;


import com.rshu.schedule.dto.ScheduleDto;
import com.rshu.schedule.entities.ScheduleRecord;
import com.rshu.schedule.repos.ScheduleRepo;
import com.rshu.schedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFullSchedule(){
        return new ResponseEntity<List<ScheduleRecord>>(scheduleService.allRecords(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleDto scheduleRecord){
        System.out.println(scheduleRecord);

        //ScheduleRecord record = new ScheduleRecord(scheduleRecord.getSubject(), scheduleRecord.getGroup(), scheduleRecord.getTeacher());
        //scheduleRepo.save(record);
        return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
    }

}
