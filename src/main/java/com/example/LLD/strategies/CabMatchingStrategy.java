package com.example.LLD.strategies;

import com.example.LLD.model.Cab;
import com.example.LLD.model.Location;
import com.example.LLD.model.Rider;

import java.util.List;

public interface CabMatchingStrategy {
    Cab matchCabToRider(Rider rider, List<Cab> cabs, Location source, Location destination);
}
