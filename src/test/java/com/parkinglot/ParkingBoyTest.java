package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    void should_return_a_ticket_when_park_given_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //When
        Ticket ticket = parkingLot.park(car);

        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_a_car_when_park_given_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);

        //When
        Car fetched_car = parkingLot.fetch(ticket);

        //Then
        assertEquals(car, fetched_car);
    }

    @Test
    void should_return_right_car_when_park_given_two_tickets() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        //When
        Car fetched_car1 = parkingLot.fetch(ticket1);
        Car fetched_car2 = parkingLot.fetch(ticket2);

        //Then
        assertEquals(car1, fetched_car1);
        assertEquals(car2, fetched_car2);
    }

    @Test
    void should_retrun_none_with_error_msg_when_park_given_a_wrong_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> parkingLot.fetch(new Ticket()));
    }

    @Test
    void should_retrun_none_with_error_msg_when_park_given_a_used_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> parkingLot.fetch(ticket));
    }

    @Test
    void should_return_none_with_error_msg_when_park_given_a_car_and_no_vacancy() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 10; i++) {
            parkingLot.park(new Car());
        }

        // When & Then
        assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(new Car()));
    }

}