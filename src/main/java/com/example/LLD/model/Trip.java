package com.example.LLD.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

enum TripStatus {
    IN_PROGRESS,
    FINISHED;
}

@Getter
@ToString
public class Trip {
    private Cab cab;
    private Rider rider;
    private Location origin;
    private Location destination;
    private Double price;
    private TripStatus tripStatus;

    public Trip(@NonNull Cab cab,
                @NonNull Rider rider,
                @NonNull Location origin,
                @NonNull Location destination,
                @NonNull Double price) {
        this.cab = cab;
        this.rider = rider;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.tripStatus = TripStatus.IN_PROGRESS;
    }

    public void endTrip() {
        this.tripStatus = TripStatus.FINISHED;
    }
}
