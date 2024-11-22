package com.parkinglot;

public class UnrecognizedParkingTicketExpection extends RuntimeException {
    public UnrecognizedParkingTicketExpection() {
        super("Unrecognized parking ticket.");
    }
}
