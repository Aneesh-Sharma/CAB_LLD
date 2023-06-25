package com.example.LLD.exceptions;

public class TripNotFoundException extends RuntimeException{
    public TripNotFoundException() {
        super("trip not found");
    }
}
