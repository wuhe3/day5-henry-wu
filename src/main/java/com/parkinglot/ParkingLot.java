package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private int currentVacancy;
    private String parkingLogId;
    private int maxVaccancy;

    public ParkingLot(int maxVaccancy) {
        this.parkingLogId = UUID.randomUUID().toString();
        this.maxVaccancy = maxVaccancy;
        this.currentVacancy = maxVaccancy;
    }

    public Ticket park(Car car) {
        if (currentVacancy == 0) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket(parkingLogId);
        parkingRecords.put(ticket, car);
        currentVacancy--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = parkingRecords.get(ticket);
        if (car != null) {
            parkingRecords.remove(ticket);
            currentVacancy++;
        } else {
            throw new UnrecognizedParkingTicketExpection();
        }
        return car;
    }

    public boolean isFull() {
        return (currentVacancy == 0);
    }

    public boolean isTicketValid(Ticket ticket) {
        return parkingRecords.containsKey(ticket);
    }

    public String getParkingLotId() {
        return parkingLogId;
    }

    public int getCurrentVacancy() {
        return currentVacancy;
    }

    public int getAvailablePositionRate() {
        return currentVacancy / maxVaccancy;
    }




}