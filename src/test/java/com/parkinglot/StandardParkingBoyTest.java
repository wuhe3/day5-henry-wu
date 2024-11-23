package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {
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
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();

        //When
        Ticket ticket = parkingLot.park(car);

        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_a_car_when_park_given_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(10);
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
        ParkingLot parkingLot = new ParkingLot(10);
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
        ParkingLot parkingLot = new ParkingLot(10);

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> parkingLot.fetch(new Ticket("wrong")));
    }

    @Test
    void should_retrun_none_with_error_msg_when_park_given_a_used_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> parkingLot.fetch(ticket));
    }

    @Test
    void should_return_none_with_error_msg_when_park_given_a_car_and_no_vacancy() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        for (int i = 0; i < 10; i++) {
            parkingLot.park(new Car());
        }

        // When & Then
        assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(new Car()));
    }

    // story 4 case 1
    @Test
    void should_parked_in_first_park_when_park_given_1_car_and_2_parking_log() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.assign(parkingLot1);
        standardParkingBoy.assign(parkingLot2);
        Car car = new Car();

        // When
        Ticket ticket = standardParkingBoy.park(car);
        String parkingLotId = ticket.getParkingLotId();

        // Then
        assertEquals(parkingLotId, parkingLot1.getParkingLotId());
    }

    // story 4 case 2
    @Test
    void should_parked_in_second_park_when_park_given_1_car_and_2_parking_log_and_first_parking_lot_is_full() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.assign(parkingLot1);
        standardParkingBoy.assign(parkingLot2);
        for (int i = 0; i < 10; i++) {
            parkingLot1.park(new Car());
        }
        Car car = new Car();

        // When
        Ticket ticket = standardParkingBoy.park(car);
        String parkingLotId = ticket.getParkingLotId();

        // Then
        assertEquals(parkingLotId, parkingLot2.getParkingLotId());
    }

    // story 4 case 3
    @Test
    void should_return_right_car_when_park_given_two_parking_lots_and_two_cars() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.assign(parkingLot1);
        standardParkingBoy.assign(parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = standardParkingBoy.park(car1);
        Ticket ticket2 = standardParkingBoy.park(car2);

        // When
        Car fetched_car1 = standardParkingBoy.fetch(ticket1);
        Car fetched_car2 = standardParkingBoy.fetch(ticket2);

        // Then
        assertEquals(car1, fetched_car1);
        assertEquals(car2, fetched_car2);
    }

    // story 4 case 4
    @Test
    void should_return_none_with_error_msg_when_park_given_a_wrong_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.assign(parkingLot1);
        standardParkingBoy.assign(parkingLot2);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(ticket);

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> standardParkingBoy.fetch(new Ticket("wrong")));
    }

    // story 4 case 5
    @Test
    void should_return_none_with_error_msg_when_park_given_a_used_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.assign(parkingLot1);
        standardParkingBoy.assign(parkingLot2);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(ticket);

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> standardParkingBoy.fetch(ticket));
    }

    // story 4 case 6
    @Test
    void should_return_none_with_error_msg_when_park_given_a_car_and_no_vacancy_in_both_lots() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.assign(parkingLot1);
        standardParkingBoy.assign(parkingLot2);
        for (int i = 0; i < 10; i++) {
            parkingLot1.park(new Car());
            parkingLot2.park(new Car());
        }

        // When & Then
        assertThrows(NoAvailablePositionException.class, () -> standardParkingBoy.park(new Car()));
    }


}




