package com.rshu.schedule.schedule;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFullSchedule(){
        return new ResponseEntity<List<ScheduleDto>>(scheduleService.allRecords(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleDto scheduleRecord){
        return new ResponseEntity<Boolean>(scheduleService.createScheduleRecord(scheduleRecord),HttpStatus.CREATED);
    }

}
