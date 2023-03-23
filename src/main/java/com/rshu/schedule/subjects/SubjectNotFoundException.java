package com.rshu.schedule.subjects;

import com.rshu.schedule.exceptions.Entities;
import com.rshu.schedule.exceptions.EntityNotFoundException;

public class SubjectNotFoundException extends EntityNotFoundException {
    public SubjectNotFoundException(String message) {
        super(Entities.SUBJECT.name());
    }
}
