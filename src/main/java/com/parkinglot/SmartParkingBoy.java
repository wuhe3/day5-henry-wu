package com.parkinglot;

import java.util.Comparator;

public class SmartParkingBoy extends StandardParkingBoy {
    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLotWithMoreEmptyPositions = parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getCurrentVacancy))
                .orElseThrow(NoAvailablePositionException::new);
        return parkingLotWithMoreEmptyPositions.park(car);
    }
}
