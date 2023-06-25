package com.example.LLD.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Location {
    private Double x;
    private Double y;

    public Double distance(Location location) {
        return Math.sqrt(Math.pow(location.getX() - x, 2) + Math.pow(location.getY() - y, 2));
    }

}
