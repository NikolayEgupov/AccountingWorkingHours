package ru.egupov.accountingworkinghours.util.error;

import java.util.function.Supplier;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
