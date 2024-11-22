package com.parkinglot;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class ParkingLot {
    private Map<Ticket, Car> parkingRecords = new HashMap<>();

    public Ticket park(Car car) {
        Ticket ticket = new Ticket(UUID.randomUUID());
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return parkingRecords.get(ticket);
    }
}
