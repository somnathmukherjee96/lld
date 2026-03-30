package elevator.models;

import elevator.enums.Button;

import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<Elevator> elevators;
    private Button button;

    public Floor(int floorNumber, List<Elevator> elevators) {
        this.floorNumber = floorNumber;
        this.elevators = elevators;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void clickButton(Button button){
        this.button  = button;
    }
}
