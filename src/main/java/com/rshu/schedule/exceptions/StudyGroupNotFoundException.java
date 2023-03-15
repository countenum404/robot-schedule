package com.rshu.schedule.exceptions;

public class StudyGroupNotFoundException extends EntityNotFoundException{
    public StudyGroupNotFoundException(String message) {
        super(Entities.STUDY_GROUP.name());
    }
}
