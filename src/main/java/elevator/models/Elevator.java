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

    public Elevator(Floor currentFloor) {
        this.id = UUID.randomUUID().toString();
        this.currentFloor = currentFloor;
        this.currentState = ElevatorState.IDLE;
        this.observers = Collections.synchronizedList(new ArrayList<>());
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

    public boolean isIdle() {
        return currentState == ElevatorState.IDLE;
    }

    public synchronized void addRequest(Floor floor, Direction direction) {
        if (direction == Direction.UP) upQueue.offer(floor);
        else downQueue.offer(floor);
    }

    public void move() throws InterruptedException {
        if (!upQueue.isEmpty()) {
            currentDirection = Direction.UP;
            moveUp();
        }

        if (!downQueue.isEmpty()) {
            currentDirection = Direction.DOWN;
            moveDown();
        }
    }

    public void moveUp() throws InterruptedException {
        currentState = ElevatorState.MOVING_UP;
        while (!upQueue.isEmpty()) {
            currentFloor = upQueue.poll();
            Thread.sleep(2000);
            notifyObserver();
        }
    }

    public void moveDown() throws InterruptedException {
        currentState = ElevatorState.MOVING_DOWN;
        while (!downQueue.isEmpty()) {
            currentFloor = downQueue.poll();
            Thread.sleep(2000);
            notifyObserver();
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
