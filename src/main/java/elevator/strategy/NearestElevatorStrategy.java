package elevator.strategy;

import elevator.models.Elevator;
import elevator.models.Request;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        int targetFloor = request.getTargetFloor().getFloorNumber();

        return elevators.stream()
                .min((e1, e2) -> {
                    int penalty1 = e1.isIdle() ? 0 : 3;
                    int penalty2 = e2.isIdle() ? 0 : 3;

                    int dist1 = Math.abs(targetFloor - e1.getCurrentFloor().getFloorNumber()) + penalty1;
                    int dist2 = Math.abs(targetFloor - e2.getCurrentFloor().getFloorNumber()) + penalty2;

                    return Integer.compare(dist1, dist2);
                })
                .orElseThrow(() -> new RuntimeException("No Elevators available"));
    }
}
