package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private int vacancy;
    private String parkingLogId;
    private final int capacity;

    public ParkingLot(int capacity) {
        this.parkingLogId = UUID.randomUUID().toString();
        this.capacity = capacity;
        this.vacancy = capacity;
    }

    public Ticket park(Car car) {
        if (vacancy == 0) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket(parkingLogId);
        parkingRecords.put(ticket, car);
        vacancy--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = parkingRecords.get(ticket);
        if (car != null) {
            parkingRecords.remove(ticket);
            vacancy++;
        } else {
            throw new UnrecognizedParkingTicketExpection();
        }
        return car;
    }

    public boolean isFull() {
        return (vacancy == 0);
    }

    public boolean isTicketValid(Ticket ticket) {
        return parkingRecords.containsKey(ticket);
    }

    public String getParkingLotId() {
        return parkingLogId;
    }

    public int getVacancy() {
        return vacancy;
    }

    public int getAvailablePositionRate() {
        return vacancy / capacity;
    }




}