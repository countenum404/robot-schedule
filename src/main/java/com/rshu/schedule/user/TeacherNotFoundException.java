package com.rshu.schedule.user;

import com.rshu.schedule.exceptions.Entities;
import com.rshu.schedule.exceptions.EntityNotFoundException;

public class TeacherNotFoundException extends EntityNotFoundException {
    public TeacherNotFoundException(String message) {
        super(Entities.TEACHER.name());
    }
}
