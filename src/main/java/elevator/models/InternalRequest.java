package elevator.models;

import elevator.enums.Direction;

public class InternalRequest implements Request {
    private final Floor targetFloor;
    private final Elevator elevator;

    public InternalRequest(Elevator elevator, Floor targetFloor) {
        this.targetFloor = targetFloor;
        this.elevator = elevator;
    }

    @Override
    public Floor getTargetFloor() {
        return targetFloor;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public Direction getDirection() {
        return targetFloor.getFloorNumber() > elevator.getCurrentFloor().getFloorNumber()
                ? Direction.UP
                : Direction.DOWN;
    }
}
