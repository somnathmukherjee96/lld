package elevator.models;

import elevator.enums.Button;

public class Floor {
    private final int floorNumber;
    private Button button;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void clickButton(Button button){
        this.button  = button;
    }
}
