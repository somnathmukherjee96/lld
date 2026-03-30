package elevator.models;

import elevator.ElevatorController;
import elevator.enums.ElevatorState;
import elevator.strategy.ElevatorSelectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private final List<Floor> floors;
    private final List<Elevator> elevators;
    private final ElevatorController elevatorController;

    private static Building instance;

    public static Building getInstance(int numOfFloors, int numOfElevators, ElevatorSelectionStrategy elevatorSelectionStrategy) {
        if (instance == null) {
            synchronized (Building.class) {
                if (instance == null)
                    instance = new Building(numOfFloors, numOfElevators, elevatorSelectionStrategy);
            }
        }
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
        for (int i = 0; i < numOfElevators; i++)
            elevators.add(new Elevator(new Floor(0), ElevatorState.IDLE));
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
}
