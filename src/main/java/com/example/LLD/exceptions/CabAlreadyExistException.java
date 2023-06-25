package com.example.LLD.exceptions;

public class CabAlreadyExistException extends RuntimeException{
    public CabAlreadyExistException() {
        super("CAB ALREADY EXISTS");
    }
}
