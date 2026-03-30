package elevator.observer;

public interface ElevatorObservable {
    void addObserver(ElevatorObserver observer);
    void removeObserver(ElevatorObserver observer);
    void notifyObserver();
}
