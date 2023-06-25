package com.example.LLD.exceptions;

public class NoCabAvailableException extends RuntimeException {
    public NoCabAvailableException() {
        super("No Cab Available");
    }
}
