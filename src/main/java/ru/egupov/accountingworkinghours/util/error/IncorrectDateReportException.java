package ru.egupov.accountingworkinghours.util.error;

public class IncorrectDateReportException extends RuntimeException{
    public IncorrectDateReportException(String message) {
        super(message);
    }
}
