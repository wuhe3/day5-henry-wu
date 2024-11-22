package com.parkinglot;

import java.util.UUID;

public class Ticket {
    private UUID ticketId;

    public Ticket(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public UUID getTicketId() {
        return ticketId;
    }
}
