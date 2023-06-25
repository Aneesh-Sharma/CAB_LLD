package com.example.LLD.service;

import com.example.LLD.exceptions.NoCabAvailableException;
import com.example.LLD.exceptions.TripNotFoundException;
import com.example.LLD.model.Cab;
import com.example.LLD.model.Location;
import com.example.LLD.model.Rider;
import com.example.LLD.model.Trip;
import com.example.LLD.strategies.CabMatchingStrategy;
import com.example.LLD.strategies.PricingStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TripService {
    Map<String, List<Trip>> trips = new HashMap<>();

    private CabService cabService;
    private CabMatchingStrategy cabMatchingStrategy;
    private PricingStrategy pricingStrategy;
    private final Double MAX_CAB_DISTANCE = 100.0;


    public TripService(CabService cabService, CabMatchingStrategy cabMatchingStrategy, PricingStrategy pricingStrategy) {
        this.cabService = cabService;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void createTrip(Rider rider, Location source, Location destination) {
        List<Cab> availableCabs = cabService.getCabs(source, MAX_CAB_DISTANCE);
        Cab assignedCab = cabMatchingStrategy.matchCabToRider(rider, availableCabs, source, destination);
        if(assignedCab == null) {
            throw new NoCabAvailableException();
        }
        Double price = pricingStrategy.findPrice(rider, source, destination);
        Trip newTrip = new Trip(assignedCab, rider, source, destination, price);
        List<Trip> userTrips = trips.get(rider.getId());
        if(userTrips == null) {
            userTrips = new ArrayList<>();
        }
        userTrips.add(newTrip);
        trips.put(rider.getId(), userTrips);
        assignedCab.setCurrentTrip(newTrip);
        assignedCab.setIsAvailable(Boolean.FALSE);
    }

    public List<Trip> fetchHistory(Rider rider) {
        return trips.get(rider.getId());
    }

    public void endTrip(Cab cab) {
        if(cab.getCurrentTrip() == null) {
            throw new TripNotFoundException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }


}
