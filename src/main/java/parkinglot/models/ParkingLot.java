package parkinglot.models;

import parkinglot.observer.ParkingLotObservable;
import parkinglot.observer.ParkingLotObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ParkingLot implements ParkingLotObservable {
    private static ParkingLot instance;

    private final String id;
    private List<ParkingFloor> parkingFloors;
    private List<ParkingLotObserver> observers;

    private ParkingLot() {
        this.id = UUID.randomUUID().toString();
        this.parkingFloors = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public static ParkingLot getInstance() {
        if (instance == null)
            instance = new ParkingLot();
        return instance;
    }

    public String getId() {
        return id;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public boolean addFloor(ParkingFloor floor) {
        return this.parkingFloors.add(floor);
    }

    @Override
    public void addObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(ParkingLotObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ParkingLotObserver observer : observers)
            observer.update(this);
    }
}
