package com.parkinglot;

import java.util.UUID;

public class Ticket {
    private UUID ticketId;

    public Ticket() {
        this.ticketId = UUID.randomUUID();
    }

    public UUID getTicketId() {
        return ticketId;
    }
}
