package elevator.strategy;

import elevator.models.Elevator;
import elevator.models.Floor;
import elevator.models.Request;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        Floor targetFloor = request.getTargetFloor();
        int minDist = Integer.MAX_VALUE;
        Elevator e = null;
        for (Elevator elevator : elevators) {
            int distDiff = Math.abs(elevator.getCurrentFloor().getFloorNumber() - targetFloor.getFloorNumber());
            if (distDiff < minDist) {
                minDist = distDiff;
                e = elevator;
            }
        }
        return e;
    }
}
