package com.parkinglot;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmartParkingBoyTest {
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


}
