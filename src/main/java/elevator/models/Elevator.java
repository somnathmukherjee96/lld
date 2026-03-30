package elevator.models;

import elevator.enums.Direction;
import elevator.enums.ElevatorState;

import java.util.UUID;

public class Elevator {
    private final String id;
    private Floor currentFloor;
    private ElevatorState currentState;
    private Direction currentDirection;
    private ElevatorQueue elevatorQueue;

    public Elevator(Floor currentFloor, ElevatorState currentState, Direction currentDirection, ElevatorQueue elevatorQueue) {
        this.id = UUID.randomUUID().toString();
        this.currentFloor = currentFloor;
        this.currentState = currentState;
        this.currentDirection = currentDirection;
        this.elevatorQueue = elevatorQueue;
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

    public ElevatorQueue getElevatorQueue() {
        return elevatorQueue;
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
}
