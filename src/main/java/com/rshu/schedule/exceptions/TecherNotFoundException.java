package com.rshu.schedule.exceptions;

public class TecherNotFoundException extends EntityNotFoundException{
    public TecherNotFoundException(String message) {
        super(Entities.TEACHER.name());
    }
}
