package com.parkinglot;
import java.util.Comparator;

public class SuperParkingBoy extends StandardParkingBoy {
    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLotWithMoreEmptyPositions = parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                .orElseThrow(NoAvailablePositionException::new);
        return parkingLotWithMoreEmptyPositions.park(car);
    }
}
