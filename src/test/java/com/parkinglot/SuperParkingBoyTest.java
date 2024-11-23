package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SuperParkingBoyTest {
    @Test
    void should_park_in_the_parking_lot_with_higher_empty_positions_rate_when_park_given_one_car() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(20);
        ParkingLot parkingLot2 = new ParkingLot(15);
        parkingLot1.park(new Car());
        parkingLot2.park(new Car());
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

        //When
        Ticket ticket = superParkingBoy.park(car);
        String parkingLotId = ticket.getParkingLotId();

        //Then
        assertEquals(parkingLot1.getParkingLotId(), parkingLotId);
    }

}
