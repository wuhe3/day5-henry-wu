package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class FirstAvailableStrategy implements ParkingStrategy {
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        ParkingLot parkingLotWithMoreEmptyPositions = parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getVacancy))
                .orElseThrow(NoAvailablePositionException::new);
        return parkingLotWithMoreEmptyPositions.park(car);
    }
}