package com.rshu.schedule.exceptions;

public class TeacherNotFoundException extends EntityNotFoundException{
    public TeacherNotFoundException(String message) {
        super(Entities.TEACHER.name());
    }
}
