package parkinglot.observer;

import parkinglot.enums.VehicleType;
import parkinglot.models.ParkingFloor;
import parkinglot.models.ParkingSpot;

import java.util.List;
import java.util.Map;

public interface ParkingLotObservable {
    void addObserver(ParkingLotObserver observer);
    void notifyObservers();
}