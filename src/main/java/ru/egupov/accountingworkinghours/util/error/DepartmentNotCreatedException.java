package ru.egupov.accountingworkinghours.util.error;

public class DepartmentNotCreatedException extends RuntimeException{
    public DepartmentNotCreatedException(String message) {
        super("The department was not created! " + message);
    }
}
