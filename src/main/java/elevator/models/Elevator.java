package elevator.models;

import elevator.enums.Direction;
import elevator.enums.ElevatorState;
import elevator.observer.ElevatorObservable;
import elevator.observer.ElevatorObserver;

import java.util.*;

public class Elevator implements ElevatorObservable {
    private final String id;
    private Floor currentFloor;
    private ElevatorState currentState;
    private Direction currentDirection;
    private final PriorityQueue<Floor> upQueue;
    private final PriorityQueue<Floor> downQueue;
    private List<ElevatorObserver> observers;

    public Elevator(Floor currentFloor, ElevatorState currentState) {
        this.id = UUID.randomUUID().toString();
        this.currentFloor = currentFloor;
        this.currentState = currentState;
        this.observers = new ArrayList<>();
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>(Collections.reverseOrder());
    }

    public String getId() {
        return id;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorState getCurrentState() {
        return currentState;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setCurrentState(ElevatorState currentState) {
        this.currentState = currentState;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void move(Floor targetFloor) throws InterruptedException {
        if (!upQueue.isEmpty()) currentDirection = Direction.UP;
        else if (!downQueue.isEmpty()) currentDirection = Direction.DOWN;
        else return;

        if (currentDirection.equals(Direction.UP))
            moveUp(targetFloor);
        else
            moveDown(targetFloor);
    }

    public void moveUp(Floor targetFloor) throws InterruptedException {
        while (!upQueue.isEmpty()) {
            if (!currentState.equals(ElevatorState.DOOR_OPEN)) {
                Floor currentFloor = upQueue.poll();
                this.currentFloor = currentFloor;
                notifyObserver();
                if (currentFloor.getFloorNumber() == targetFloor.getFloorNumber())
                    break;
                Thread.sleep(2000);
            }
        }
    }

    public void moveDown(Floor targetFloor) throws InterruptedException {
        while (!downQueue.isEmpty()) {
            if (!currentState.equals(ElevatorState.DOOR_OPEN)) {
                Floor currentFloor = downQueue.poll();
                this.currentFloor = currentFloor;
                notifyObserver();
                if (currentFloor.getFloorNumber() == targetFloor.getFloorNumber())
                    break;
                Thread.sleep(2000);
            }
        }
    }

    @Override
    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ElevatorObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (ElevatorObserver observer : observers)
            observer.update(this);
    }
}
