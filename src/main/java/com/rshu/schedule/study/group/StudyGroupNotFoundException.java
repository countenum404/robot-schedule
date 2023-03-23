package com.rshu.schedule.study.group;

import com.rshu.schedule.exceptions.Entities;
import com.rshu.schedule.exceptions.EntityNotFoundException;

public class StudyGroupNotFoundException extends EntityNotFoundException {
    public StudyGroupNotFoundException(String message) {
        super(Entities.STUDY_GROUP.name());
    }
}
