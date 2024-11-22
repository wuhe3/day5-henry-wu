package com.parkinglot;

import java.util.Objects;
import java.util.UUID;

public class Ticket {
    private String ticketId;
    private final String parkingLogId;

    public Ticket(String parkingLogId) {
        this.ticketId = UUID.randomUUID().toString();
        this.parkingLogId = parkingLogId;
    }

    public String getTicketId() {
        return ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    public String getParkingLotId() {
        return parkingLogId;
    }
}
