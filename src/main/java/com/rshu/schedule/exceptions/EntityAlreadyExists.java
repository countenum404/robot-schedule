package com.rshu.schedule.exceptions;

public abstract class EntityAlreadyExists extends Exception{
    public EntityAlreadyExists() {
        super();
    }

    public EntityAlreadyExists(String message) {
        super(message + " already exists");
    }
}
