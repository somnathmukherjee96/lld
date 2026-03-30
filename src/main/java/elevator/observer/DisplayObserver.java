package elevator.observer;

import elevator.models.Elevator;

public class DisplayObserver implements ElevatorObserver {
    @Override
    public void update(Elevator elevator) {
        System.out.println("The elevator " + elevator.getId() + " is at floor-" + elevator.getCurrentFloor().getFloorNumber());
    }
}
