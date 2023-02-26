package ru.egupov.accountingworkinghours.util.error;

public class EventWorkNotCreatedException extends RuntimeException{

    public EventWorkNotCreatedException(String message) {
        super("Событие не создано! " + message);
    }
}
