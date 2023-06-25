package com.example.LLD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cab {
    private String id;
    private String driverName;

    @Setter
    private Trip currentTrip;
    @Setter
    private Boolean isAvailable;
    @Setter
    private Location currentLocation;

    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id='" + id + '\'' +
                ", driverName='" + driverName + '\'' +
                ", isAvailable=" + isAvailable +
                ", currentLocation=" + currentLocation +
                '}';
    }

}
