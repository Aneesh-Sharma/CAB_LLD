package com.example.LLD.strategies;

import com.example.LLD.model.Cab;
import com.example.LLD.model.Location;
import com.example.LLD.model.Rider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCabMatchingStrategy implements CabMatchingStrategy{
    @Override
    public Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location source, Location destination) {
        if(candidateCabs.isEmpty()) {
            return null;
        }
        return candidateCabs.get(0);
    }
}
