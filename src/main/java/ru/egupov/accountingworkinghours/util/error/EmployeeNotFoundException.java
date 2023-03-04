package ru.egupov.accountingworkinghours.util.error;

import java.util.function.Supplier;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
