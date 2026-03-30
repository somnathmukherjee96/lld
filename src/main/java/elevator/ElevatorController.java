package elevator;

import elevator.models.Elevator;
import elevator.models.ExternalRequest;
import elevator.models.InternalRequest;
import elevator.models.Request;
import elevator.strategy.ElevatorSelectionStrategy;

import java.util.List;

public class ElevatorController {
    private final ElevatorSelectionStrategy elevatorSelectionStrategy;
    private final List<Elevator> elevators;


    public ElevatorController(List<Elevator> elevators, ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.elevators = elevators;
        this.elevatorSelectionStrategy = elevatorSelectionStrategy;
    }

    public void handleRequest(Request request) throws InterruptedException {
        if (request instanceof InternalRequest r) handleInternalRequest(r);
        else if (request instanceof ExternalRequest r) handleExternalRequest(r);
    }

    private void handleInternalRequest(InternalRequest request) throws InterruptedException {
        Elevator elevator = request.getElevator();
        elevator.addRequest(request.getTargetFloor(), request.getDirection());
        elevator.move();
    }

    private void handleExternalRequest(ExternalRequest request) throws InterruptedException {
        Elevator elevator = elevatorSelectionStrategy.selectElevator(elevators, request);
        elevator.addRequest(request.getTargetFloor(), request.getDirection());
        elevator.move();
    }
}
