package parkinglot.observer;

public interface ParkingLotObservable {
    void addObserver(ParkingLotObserver observer);
    void removeObserver(ParkingLotObserver observer);
    void notifyObservers();
}