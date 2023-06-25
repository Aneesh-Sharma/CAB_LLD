package com.example.LLD.strategies;

import com.example.LLD.model.Location;
import com.example.LLD.model.Rider;

public interface PricingStrategy {
    Double findPrice(Rider rider, Location source, Location destination);
}
