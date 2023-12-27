package com.rest.crud.restcrudexample.exception;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(Long id) {
        super(String.format("Driver not found with ID: %s", id));
    }

}
