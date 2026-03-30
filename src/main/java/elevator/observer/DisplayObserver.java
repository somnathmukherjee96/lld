package elevator.observer;

import elevator.models.Elevator;

public class DisplayObserver implements ElevatorObserver {
    @Override
    public void update(Elevator elevator) {
        System.out.printf("  [Display] Elevator %s arrived at %s (state: %s)%n",
                elevator.getId().substring(0, 6),
                elevator.getCurrentFloor().getFloorNumber(),
                elevator.getCurrentState());
    }
}
