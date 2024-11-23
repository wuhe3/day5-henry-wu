package com.parkinglot;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    // Story 5 Case 1
    @Test
    void should_park_in_the_first_parking_lot_when_both_have_same_empty_positions() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.assign(parkingLot1);
        smartParkingBoy.assign(parkingLot2);
        Car car = new Car();

        // When
        Ticket ticket = smartParkingBoy.park(car);
        String parkingLotId = ticket.getParkingLotId();

        // Then
        assertEquals(parkingLot1.getParkingLotId(), parkingLotId);
    }

    // Story 5 Case 2
    @Test
    void should_park_in_the_parking_lot_with_more_empty_positions_when_park_given_one_car() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.park(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.assign(parkingLot1);
        smartParkingBoy.assign(parkingLot2);
        Car car = new Car();

        //When
        Ticket ticket = smartParkingBoy.park(car);
        String parkingLotId = ticket.getParkingLotId();

        //Then
        assertEquals(parkingLot2.getParkingLotId(), parkingLotId);
    }

    // Story 5 Case 3
    @Test
    void should_return_right_car_when_fetch_given_two_parking_lots_and_two_tickets() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.assign(parkingLot1);
        smartParkingBoy.assign(parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = smartParkingBoy.park(car1);
        Ticket ticket2 = smartParkingBoy.park(car2);

        // When
        Car fetchedCar1 = smartParkingBoy.fetch(ticket1);
        Car fetchedCar2 = smartParkingBoy.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    // Story 5 Case 4
    @Test
    void should_return_none_with_error_msg_when_fetch_given_an_unrecognized_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.assign(parkingLot1);
        smartParkingBoy.assign(parkingLot2);

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> smartParkingBoy.fetch(new Ticket("wrong")));
    }

    // Story 5 Case 5
    @Test
    void should_return_none_with_error_msg_when_fetch_given_a_used_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.assign(parkingLot1);
        smartParkingBoy.assign(parkingLot2);
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(ticket);

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> smartParkingBoy.fetch(ticket));
    }

    // Story 5 Case 6
    @Test
    void should_return_none_with_error_msg_when_park_given_no_vacancy_in_both_lots() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.assign(parkingLot1);
        smartParkingBoy.assign(parkingLot2);
        for (int i = 0; i < 10; i++) {
            parkingLot1.park(new Car());
            parkingLot2.park(new Car());
        }

        // When & Then
        assertThrows(NoAvailablePositionException.class, () -> smartParkingBoy.park(new Car()));
    }

}
