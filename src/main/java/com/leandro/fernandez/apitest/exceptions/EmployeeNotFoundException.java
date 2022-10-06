package com.leandro.fernandez.apitest.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException (Long id){
        super ("Could not find employee " + id);
    }
}
