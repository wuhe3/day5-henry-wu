package com.parkinglot;

public class SmartParkingBoy extends StandardParkingBoy {
    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLotWithMoreEmptyPositions = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getVaccancy() > parkingLotWithMoreEmptyPositions.getVaccancy()) {
                parkingLotWithMoreEmptyPositions = parkingLot;
            }
        }
        if (parkingLotWithMoreEmptyPositions.isFull()) {
            throw new NoAvailablePositionException();
        } else {
            return parkingLotWithMoreEmptyPositions.park(car);
        }

    }
}
