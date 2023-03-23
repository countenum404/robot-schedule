package com.rshu.schedule.user;

import com.rshu.schedule.exceptions.Entities;
import com.rshu.schedule.exceptions.EntityNotFoundException;

public class StudentNotFoundException extends EntityNotFoundException {
    public StudentNotFoundException(String message) {
        super(Entities.STUDENT.name());
    }
}
