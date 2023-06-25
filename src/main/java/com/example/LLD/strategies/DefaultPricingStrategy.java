package com.example.LLD.strategies;

import com.example.LLD.model.Location;
import com.example.LLD.model.Rider;
import org.springframework.stereotype.Component;

@Component
public class DefaultPricingStrategy implements PricingStrategy{
    private Double PER_KM_RATE = 15.0;

    @Override
    public Double findPrice(Rider rider, Location source, Location destination) {
        return source.distance(destination) * PER_KM_RATE;
    }
}
