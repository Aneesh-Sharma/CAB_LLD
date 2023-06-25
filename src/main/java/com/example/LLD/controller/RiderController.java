package com.example.LLD.controller;

import com.example.LLD.model.Location;
import com.example.LLD.model.Rider;
import com.example.LLD.model.Trip;
import com.example.LLD.service.RiderService;
import com.example.LLD.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RiderController {

    RiderService riderService;
    TripService tripService;

    public RiderController(RiderService riderService, TripService tripService) {
        this.riderService = riderService;
        this.tripService = tripService;
    }

    @RequestMapping(value = "/rider/register", method = RequestMethod.POST)
    public ResponseEntity registerRider(final String riderId, final String name) {
        riderService.createRider(new Rider(riderId, name));
       return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity book(final String riderId,
                               final Double sourceX,
                               final Double sourceY,
                               final Double destX,
                               final Double destY) {
        tripService.createTrip(riderService.getRider(riderId), new Location(sourceX, sourceY), new Location(destX, destY));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity fetchHistory(final String riderId) {
       List<Trip> trips = tripService.fetchHistory(riderService.getRider(riderId));
       return ResponseEntity.ok(trips);
    }

}


// register
// book
// retrieve Booking