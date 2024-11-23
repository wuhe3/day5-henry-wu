package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SuperParkingBoyTest {
    // Case 1
    @Test
    void should_park_in_the_parking_lot_with_higher_empty_positions_rate_when_park_given_one_car() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(20);
        ParkingLot parkingLot2 = new ParkingLot(15);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        superParkingBoy.assign(parkingLot1);
        superParkingBoy.assign(parkingLot2);
        Car car = new Car();
        // 25% full
        for (int i = 0; i < 5; i++) {
            parkingLot1.park(new Car());
        }
        // 20% full
        for (int i = 0; i < 3; i++) {
            parkingLot2.park(new Car());
        }

        // When
        Ticket ticket = superParkingBoy.park(car);
        String parkingLotId = ticket.getParkingLotId();

        // Then
        assertEquals(parkingLot2.getParkingLotId(), parkingLotId);
    }

    // Case 2
    @Test
    void should_return_right_car_when_fetch_given_two_parking_lots_and_two_tickets() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        superParkingBoy.assign(parkingLot1);
        superParkingBoy.assign(parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = superParkingBoy.park(car1);
        Ticket ticket2 = superParkingBoy.park(car2);

        // When
        Car fetchedCar1 = superParkingBoy.fetch(ticket1);
        Car fetchedCar2 = superParkingBoy.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    // Case 3
    @Test
    void should_return_none_with_error_msg_when_fetch_given_an_unrecognized_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        superParkingBoy.assign(parkingLot1);
        superParkingBoy.assign(parkingLot2);

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> superParkingBoy.fetch(new Ticket("wrong")));
    }

    // Case 4
    @Test
    void should_return_none_with_error_msg_when_fetch_given_a_used_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        superParkingBoy.assign(parkingLot1);
        superParkingBoy.assign(parkingLot2);
        Car car = new Car();
        Ticket ticket = superParkingBoy.park(car);
        superParkingBoy.fetch(ticket);

        // When & Then
        assertThrows(UnrecognizedParkingTicketExpection.class, () -> superParkingBoy.fetch(ticket));
    }

    // Case 5
    @Test
    void should_return_none_with_error_msg_when_park_given_no_vacancy_in_both_lots() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        superParkingBoy.assign(parkingLot1);
        superParkingBoy.assign(parkingLot2);
        for (int i = 0; i < 10; i++) {
            parkingLot1.park(new Car());
            parkingLot2.park(new Car());
        }

        // When & Then
        assertThrows(NoAvailablePositionException.class, () -> superParkingBoy.park(new Car()));
    }
}