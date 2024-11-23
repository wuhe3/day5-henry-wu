package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class StandardParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();

    public void assign(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        throw new NoAvailablePositionException();
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
