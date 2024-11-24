package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    private final ParkingStrategy parkingStrategy;

    public ParkingBoy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void assign(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        return parkingStrategy.park(parkingLots, car);
    }

    public Car fetch(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isTicketValid(ticket)) {
                return parkingLot.fetch(ticket);
            }
        }
        throw new UnrecognizedParkingTicketExpection();
    }
}