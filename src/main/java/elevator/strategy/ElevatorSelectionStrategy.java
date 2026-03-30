package elevator.strategy;

import elevator.models.Elevator;
import elevator.models.Request;

import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator selectElevator(List<Elevator> elevators, Request request);
}
