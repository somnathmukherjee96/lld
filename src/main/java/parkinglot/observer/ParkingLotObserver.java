package parkinglot.observer;

import parkinglot.enums.VehicleType;
import parkinglot.models.ParkingLot;
import parkinglot.models.ParkingSpot;

import java.util.List;
import java.util.Map;

public interface ParkingLotObserver {
    void update(ParkingLot parkingLot);
}
