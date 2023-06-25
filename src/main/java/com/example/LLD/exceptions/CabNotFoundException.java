package com.example.LLD.exceptions;

public class CabNotFoundException extends RuntimeException{
    public CabNotFoundException() {
        super("CAB NOT FOUND");
    }
}
