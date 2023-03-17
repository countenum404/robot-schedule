package com.rshu.schedule.exceptions;

public abstract class EntityAlreadyExistsException extends Exception{
    public EntityAlreadyExistsException() {
        super();
    }

    public EntityAlreadyExistsException(String message) {
        super(message + " already exists");
    }
}
