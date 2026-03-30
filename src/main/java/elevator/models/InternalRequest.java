package elevator.models;

public class InternalRequest implements Request {
    private final Floor targetFloor;

    public InternalRequest(Floor targetFloor) {
        this.targetFloor = targetFloor;
    }

    @Override
    public Floor getTargetFloor() {
        return targetFloor;
    }
}
