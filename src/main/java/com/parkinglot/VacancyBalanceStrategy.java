package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class VacancyBalanceStrategy implements ParkingStrategy {
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        ParkingLot parkingLotWithMoreEmptyPositions = parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                .orElseThrow(NoAvailablePositionException::new);
        return parkingLotWithMoreEmptyPositions.park(car);
    }
}