package com.example.LLD;

import com.example.LLD.controller.CabController;
import com.example.LLD.controller.RiderController;
import com.example.LLD.exceptions.CabAlreadyExistException;
import com.example.LLD.exceptions.CabNotFoundException;
import com.example.LLD.exceptions.NoCabAvailableException;
import com.example.LLD.exceptions.RiderNotFoundException;
import com.example.LLD.service.CabService;
import com.example.LLD.service.RiderService;
import com.example.LLD.service.TripService;
import com.example.LLD.strategies.CabMatchingStrategy;
import com.example.LLD.strategies.DefaultCabMatchingStrategy;
import com.example.LLD.strategies.DefaultPricingStrategy;
import com.example.LLD.strategies.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RunnerTest {
    CabController cabController;
    RiderController riderController;

    @BeforeEach
    void setUp() {
        CabService cabService = new CabService();
        RiderService riderService = new RiderService();
        PricingStrategy pricingStrategy = new DefaultPricingStrategy();
        CabMatchingStrategy cabMatchingStrategy = new DefaultCabMatchingStrategy();
        TripService tripService = new TripService(cabService, cabMatchingStrategy, pricingStrategy);

        cabController = new CabController(cabService, tripService);
        riderController = new RiderController(riderService, tripService);
    }

    @Test
    void testFlow() {
        String r1 = "r1";
        riderController.registerRider(r1, "rider1");
        String r2 = "r2";
        riderController.registerRider(r2, "rider2");
        String r3 = "r3";
        riderController.registerRider(r3, "rider3");
        String r4 = "r4";
        riderController.registerRider(r4, "rider4");

        String c1 = "c1";
        cabController.registerCab(c1, "cab1");
        String c2 = "c2";
        cabController.registerCab(c2, "cab2");
        String c3 = "c3";
        cabController.registerCab(c3, "cab3");
        String c4 = "c4";
        cabController.registerCab(c4, "cab4");
        String c5 = "c5";
        cabController.registerCab(c5, "cab5");

        cabController.updateLocation(20.0, 30.0, c1);
        cabController.updateLocation(22.0, 10.0, c2);
        cabController.updateLocation(10.0, 15.0, c3);
        cabController.updateLocation(30.0, 35.0, c4);
        cabController.updateLocation(25.0, 25.0, c5);

        cabController.updateAvailability(c2, false);
        cabController.updateAvailability(c4, false);

        riderController.book(r1, 0.0, 10.0, 100.0, 200.0);
        riderController.book(r2, 10.0, 20.0, 80.0, 70.0);

        System.out.println("\n### Printing current trips for r1 and r2");

        System.out.println(riderController.fetchHistory(r1).getBody());
        System.out.println(riderController.fetchHistory(r2).getBody());

        cabController.updateLocation(70.0, 10.0, c3);
        cabController.updateLocation(40.0, 50.0, c5);

        System.out.println("\n### Printing current trips for r1 and r2");

        System.out.println(riderController.fetchHistory(r1).getBody());
        System.out.println(riderController.fetchHistory(r2).getBody());

        cabController.endTrip(c5);

        System.out.println("\n### Printing current trips for r1 and r2");
        System.out.println(riderController.fetchHistory(r1).getBody());
        System.out.println(riderController.fetchHistory(r2).getBody());

        assertThrows(NoCabAvailableException.class, () -> {
            riderController.book(r3, 5000.0, 500.0, 100.0, 100.0);
        });

        assertThrows(RiderNotFoundException.class, () -> {
            riderController.book("abcd", 0.0, 0.0, 500.0, 500.0);
        });

        assertThrows(CabAlreadyExistException.class, () -> {
            cabController.registerCab("c1", "skjhsfkj");
        });

        assertThrows(CabNotFoundException.class, () -> {
            cabController.updateLocation(110.0, 110.0, "shss");
        });

        assertThrows(CabNotFoundException.class, () -> {
            cabController.updateAvailability("shss", false);
        });

    }
}
