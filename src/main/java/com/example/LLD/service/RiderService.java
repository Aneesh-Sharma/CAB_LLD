package com.example.LLD.service;

import com.example.LLD.exceptions.RiderAlreadyExistsException;
import com.example.LLD.exceptions.RiderNotFoundException;
import com.example.LLD.model.Rider;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RiderService {

    Map<String, Rider> riders = new HashMap<>();

    public void createRider(@NonNull final Rider newRider) {
        if(riders.containsKey(newRider.getId())) {
            throw new RiderAlreadyExistsException();
        }
        riders.put(newRider.getId(), newRider);
    }

    public Rider getRider(@NonNull final String id) {
        if(!riders.containsKey(id)) {
            throw new RiderNotFoundException();
        }
       return riders.get(id);
    }
}
