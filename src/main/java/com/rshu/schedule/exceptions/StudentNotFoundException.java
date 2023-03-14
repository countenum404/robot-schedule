package com.rshu.schedule.exceptions;

public class StudentNotFoundException extends EntityNotFoundException{
    public StudentNotFoundException(String message) {
        super(Entities.STUDENT.name());
    }
}
