package com.rshu.schedule.exceptions;

public class SubjectNotFoundException extends EntityNotFoundException{
    public SubjectNotFoundException(String message) {
        super(Entities.SUBJECT.name());
    }
}
