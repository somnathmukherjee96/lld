package elevator.observer;

import elevator.enums.ElevatorState;
import elevator.models.Elevator;

public class DoorObserver implements ElevatorObserver {
    @Override
    public void update(Elevator elevator) {
        try {
            elevator.setCurrentState(ElevatorState.DOOR_OPEN);
            System.out.printf("  [Door]    Elevator %s — door OPENING at %s%n",
                    elevator.getId().substring(0, 6),
                    elevator.getCurrentFloor().getFloorNumber());
            Thread.sleep(1000);
            System.out.printf("  [Door]    Elevator %s — door CLOSING%n",
                    elevator.getId().substring(0, 6));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
