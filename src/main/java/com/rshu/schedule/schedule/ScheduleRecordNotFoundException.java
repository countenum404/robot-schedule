package com.rshu.schedule.schedule;

import com.rshu.schedule.exceptions.Entities;
import com.rshu.schedule.exceptions.EntityNotFoundException;

public class ScheduleRecordNotFoundException extends EntityNotFoundException {
    public ScheduleRecordNotFoundException(String message) {
        super(Entities.SCHEDULE.name());
    }
}
