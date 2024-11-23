package com.parkinglot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    @Test
    void should_park_in_first_lot_when_using_first_available_strategy() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(20);
        ParkingLot parkingLot2 = new ParkingLot(15);
        FirstAvailableStrategy firstAvailableStrategy = new FirstAvailableStrategy();
        ParkingBoy parkingBoy = new ParkingBoy(firstAvailableStrategy);
        parkingBoy.assign(parkingLot1);
        parkingBoy.assign(parkingLot2);
        Car car = new Car();

        // 25% full, 15 empty
        for (int i = 0; i < 5; i++) {
            parkingLot1.park(new Car());
        }
        // 20% full, 12 empty
        for (int i = 0; i < 3; i++) {
            parkingLot2.park(new Car());
        }

        // When
        Ticket ticket = parkingBoy.park(car);
        String parkingLotId = ticket.getParkingLotId();

        // Then
        assertEquals(parkingLot1.getParkingLotId(), parkingLotId);
    }

    @Test
    void should_park_in_first_lot_when_using_vacancy_balance_strategy() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(20);
        ParkingLot parkingLot2 = new ParkingLot(15);
        VacancyBalanceStrategy vacancyBalanceStrategy = new VacancyBalanceStrategy();
        ParkingBoy parkingBoy = new ParkingBoy(vacancyBalanceStrategy);
        parkingBoy.assign(parkingLot1);
        parkingBoy.assign(parkingLot2);
        Car car = new Car();

        // 25% full, 15 empty
        for (int i = 0; i < 5; i++) {
            parkingLot1.park(new Car());
        }
        // 20% full, 12 empty
        for (int i = 0; i < 3; i++) {
            parkingLot2.park(new Car());
        }

        // When
        Ticket ticket = parkingBoy.park(car);
        String parkingLotId = ticket.getParkingLotId();

        // Then
        assertEquals(parkingLot2.getParkingLotId(), parkingLotId);
    }
}