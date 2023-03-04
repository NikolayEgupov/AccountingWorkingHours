package ru.egupov.accountingworkinghours.util.error;

public class EmployeeNotCreatedException extends RuntimeException{
    public EmployeeNotCreatedException(String message) {
        super("The new employee was not recorded. " + message);
    }
}
