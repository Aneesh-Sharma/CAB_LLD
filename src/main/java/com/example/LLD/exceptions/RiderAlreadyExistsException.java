package com.example.LLD.exceptions;

public class RiderAlreadyExistsException extends RuntimeException{
    public RiderAlreadyExistsException() {
        super("Rider Already exists");
    }
}
