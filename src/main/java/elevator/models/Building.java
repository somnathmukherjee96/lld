package elevator.models;

import elevator.ElevatorController;
import elevator.observer.DisplayObserver;
import elevator.observer.DoorObserver;
import elevator.strategy.ElevatorSelectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private final List<Floor> floors;
    private final List<Elevator> elevators;
    private final ElevatorController elevatorController;

    private static volatile Building instance;

    public static void initialize(int numOfFloors, int numOfElevators, ElevatorSelectionStrategy elevatorSelectionStrategy) {
        if (instance == null) {
            synchronized (Building.class) {
                if (instance == null)
                    instance = new Building(numOfFloors, numOfElevators, elevatorSelectionStrategy);
            }
        }
    }

    public static Building getInstance() {
        if (instance == null)
            throw new IllegalArgumentException("Building is not initialized, pls initialize.");
        return instance;
    }

    private Building(int numOfFloors, int numOfElevators, ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.floors = initFloors(numOfFloors);
        this.elevators = initElevators(numOfElevators);
        this.elevatorController = new ElevatorController(elevators, elevatorSelectionStrategy);
    }

    private List<Floor> initFloors(int numOfFloors) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < numOfFloors; i++)
            floors.add(new Floor(i));
        return floors;
    }

    private List<Elevator> initElevators(int numOfElevators) {
        List<Elevator> elevators = new ArrayList<>();
        for (int i = 0; i < numOfElevators; i++) {
            Elevator e = new Elevator(floors.get(0));
            e.addObserver(new DisplayObserver());
            e.addObserver(new DoorObserver());
            elevators.add(e);
        }
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public ElevatorController getElevatorController() {
        return elevatorController;
    }

    public Floor getFloor(int floorNumber) {
        return floors.stream()
                .filter(f -> f.getFloorNumber() == floorNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Floor " + floorNumber + " doesn't exist"));
    }
}
