package elevator;

import elevator.enums.Direction;
import elevator.models.*;
import elevator.strategy.NearestElevatorStrategy;

public class ElevatorApp {

    public static void main(String[] args) throws InterruptedException {

        // ─── 1. Initialize building ──────────────────────────────────────
        Building.initialize(10, 3, new NearestElevatorStrategy());
        Building building = Building.getInstance();

        ElevatorController controller = building.getElevatorController();

        System.out.println("=== Building initialized: 10 floors, 3 elevators ===");
        System.out.println("All elevators start at Floor-0\n");

        // ─── 2. External request — passenger on floor 3 pressing UP ─────
        // Strategy picks nearest idle elevator (all idle → picks elevator at floor 0, nearest to 3)
        controller.handleRequest(
                new ExternalRequest(building.getFloor(3), Direction.UP)
        );

        // ─── 3. External request — passenger on floor 7 pressing DOWN ───
        // Strategy picks next nearest idle elevator
        controller.handleRequest(
                new ExternalRequest(building.getFloor(7), Direction.DOWN)
        );

        // ─── 4. Internal request — passenger inside elevator 1, going to floor 6 ──
        Elevator elevator1 = building.getElevators().get(0);
        controller.handleRequest(
                new InternalRequest(elevator1, building.getFloor(6))
        );

        // ─── 5. Internal request — passenger inside elevator 2, going to floor 1 ──
        Elevator elevator2 = building.getElevators().get(1);
        controller.handleRequest(
                new InternalRequest(elevator2, building.getFloor(1))
        );

        // ─── 6. Multiple floors queued — SCAN algorithm demo ────────────
        // Elevator 3 gets requests for floors 2, 5, 8 going UP
        // Should serve them in order: 2 → 5 → 8 (min-heap ascending)
        System.out.println("\n=== SCAN algorithm demo: floors 8, 2, 5 queued on Elevator 3 ===");
        Elevator elevator3 = building.getElevators().get(2);
        elevator3.addRequest(building.getFloor(8), Direction.UP);
        elevator3.addRequest(building.getFloor(2), Direction.UP);
        elevator3.addRequest(building.getFloor(5), Direction.UP);
        elevator3.move(); // should visit: 2 → 5 → 8

        // ─── 7. DOWN sweep after UP — SCAN reversal demo ─────────────────
        System.out.println("\n=== SCAN reversal: floors 6, 4, 1 queued going DOWN on Elevator 3 ===");
        elevator3.addRequest(building.getFloor(6), Direction.DOWN);
        elevator3.addRequest(building.getFloor(4), Direction.DOWN);
        elevator3.addRequest(building.getFloor(1), Direction.DOWN);
        elevator3.move(); // should visit: 6 → 4 → 1

        System.out.println("\n=== All requests handled ===");
    }
}