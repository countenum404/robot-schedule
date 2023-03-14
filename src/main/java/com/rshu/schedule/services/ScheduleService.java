package com.rshu.schedule.services;

import com.rshu.schedule.entities.ScheduleRecord;
import com.rshu.schedule.repos.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private ScheduleRepo scheduleRepo;

    public ScheduleService(@Autowired ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public List<ScheduleRecord> allRecords(){
        return scheduleRepo.findAll();
    }
}
