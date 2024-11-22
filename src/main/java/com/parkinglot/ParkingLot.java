package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private static int vaccancy = 10;

    public Ticket park(Car car) {
        if (vaccancy == 0) {
            throw new NoAvailablePositionException();
//          return null;
        }
        Ticket ticket = new Ticket();
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

}
