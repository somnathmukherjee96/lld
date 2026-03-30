package elevator.models;

import elevator.enums.Button;
import elevator.enums.Direction;

public class ExternalRequest implements Request {
    private final Floor sourceFloor;
    private final Direction direction;

    public ExternalRequest(Floor sourceFloor, Direction direction) {
        this.sourceFloor = sourceFloor;
        this.direction = direction;
    }

    @Override
    public Floor getTargetFloor() {
        return sourceFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
