package elevator.models;

public class ExternalRequest implements Request {
    private Floor currentFloor;

    public ExternalRequest(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public Floor getTargetFloor() {
        return currentFloor;
    }
}
