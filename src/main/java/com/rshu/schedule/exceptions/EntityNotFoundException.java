package com.rshu.schedule.exceptions;


public abstract class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message + " is not found");
    }
}
