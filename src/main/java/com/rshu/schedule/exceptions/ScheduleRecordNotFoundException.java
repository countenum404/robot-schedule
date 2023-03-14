package com.rshu.schedule.exceptions;

public class ScheduleRecordNotFoundException extends EntityNotFoundException{
    public ScheduleRecordNotFoundException(String message) {
        super(Entities.SCHEDULE.name());
    }
}
