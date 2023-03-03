package ru.egupov.accountingworkinghours.util.error.reports;

public class IncorrectDateException extends RuntimeException{

    public IncorrectDateException(String message) {
        super(message);
    }
}
