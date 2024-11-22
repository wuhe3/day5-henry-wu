package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private int vaccancy = 10; // Make this non-static
    private String parkingLogId;

    public ParkingLot() {
        this.parkingLogId = UUID.randomUUID().toString();
    }

    public Ticket park(Car car) {
        if (vaccancy == 0) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket(parkingLogId);
        parkingRecords.put(ticket, car);
        vaccancy--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = parkingRecords.get(ticket);
        if (car != null) {
            parkingRecords.remove(ticket);
            vaccancy++;
        } else {
            throw new UnrecognizedParkingTicketExpection();
        }
        return car;
    }

    public boolean isFull() {
        return (vaccancy == 0);
    }

    public boolean isTicketValid(Ticket ticket) {
        return parkingRecords.containsKey(ticket);
    }

    public String getParkingLotId() {
        return parkingLogId;
    }
}