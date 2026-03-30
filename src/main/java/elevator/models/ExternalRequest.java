package elevator.models;

import elevator.enums.Button;

public class ExternalRequest implements Request {
    private Floor currentFloor;
    private Button button;

    public ExternalRequest(Floor currentFloor, Button button) {
        this.currentFloor = currentFloor;
        this.button = button;
    }

    @Override
    public Floor getTargetFloor() {
        return currentFloor;
    }

    public Button getButton() {
        return button;
    }
}
