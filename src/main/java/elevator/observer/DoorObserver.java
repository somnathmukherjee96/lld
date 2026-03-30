package elevator.observer;

import elevator.enums.ElevatorState;
import elevator.models.Elevator;

public class DoorObserver implements ElevatorObserver {
    @Override
    public void update(Elevator elevator) {
        elevator.setCurrentState(ElevatorState.DOOR_OPEN);
        System.out.println("Door is opening...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        elevator.setCurrentState(ElevatorState.DOOR_CLOSED);
        System.out.println("Door is closed");
    }
}
