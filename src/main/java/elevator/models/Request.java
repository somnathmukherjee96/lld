package elevator.models;

import elevator.enums.Direction;

public interface Request {
    Floor getTargetFloor();

    Direction getDirection();
}
