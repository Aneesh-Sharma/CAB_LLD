package com.example.LLD.controller;

import com.example.LLD.model.Cab;
import com.example.LLD.model.Location;
import com.example.LLD.service.CabService;
import com.example.LLD.service.TripService;
import org.springframework.boot.availability.AvailabilityState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabController {

    CabService cabService;
    TripService tripService;

    public CabController(CabService cabService, TripService tripService) {
        this.cabService = cabService;
        this.tripService = tripService;
    }

    @RequestMapping(value = "/register/cab", method = RequestMethod.POST)
    public ResponseEntity registerCab(final String id,final String driverName) {
        cabService.createCab(new Cab(id, driverName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/location", method = RequestMethod.POST)
    public ResponseEntity updateLocation(Double newX, Double newY, String cabId) {
        cabService.updateLocation(new Location(newX, newY), cabId);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/availability", method = RequestMethod.POST)
    public ResponseEntity updateAvailability(String cabId, Boolean newAvailability) {
        cabService.updateAvailability(newAvailability, cabId);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/end/trip", method = RequestMethod.POST)
    public ResponseEntity endTrip(String cabId) {
        tripService.endTrip(cabService.getCab(cabId));
        return ResponseEntity.ok("");
    }



}

// register cab
// update location
// update availability
// endTrip
