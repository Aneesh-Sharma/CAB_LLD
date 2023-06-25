package com.example.LLD.service;

import com.example.LLD.exceptions.CabAlreadyExistException;
import com.example.LLD.exceptions.CabNotFoundException;
import com.example.LLD.model.Cab;
import com.example.LLD.model.Location;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CabService {
    // in memory cab DB
    Map<String, Cab> cabs = new HashMap<>();

    public void createCab(@NonNull final Cab newCab) {
        if(cabs.containsKey(newCab.getId())) {
            throw new CabAlreadyExistException();
        }
        cabs.put(newCab.getId(), newCab);
    }

    public Cab getCab(@NonNull final String cabId) {
        if(!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
       return cabs.get(cabId);
    }

    public void updateLocation(@NonNull final Location newLocation,@NonNull final String cabId) {
        if(!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        Cab cab = cabs.get(cabId);
        cab.setCurrentLocation(newLocation);
    }

    public void updateAvailability(@NonNull final Boolean newAvailabilty, @NonNull final String cabId) {
        if(!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        Cab cab = cabs.get(cabId);
        cab.setIsAvailable(newAvailabilty);
    }

    public List<Cab> getCabs(@NonNull final Location fromPoint, @NonNull final Double distance) {
        List<Cab> resCab = new ArrayList<>();
        for(Cab cab : cabs.values()) {
            if(cab.getIsAvailable() && cab.getCurrentTrip() == null && fromPoint.distance(cab.getCurrentLocation()) <= distance) {
                resCab.add(cab);
            }
        }
        return resCab;
    }
}
