package parkinglot;

import parkinglot.enums.VehicleType;
import parkinglot.models.*;
import parkinglot.observer.DisplayObserver;
import parkinglot.service.ParkingLotService;
import parkinglot.strategy.*;

import java.util.List;

public class ParkingLotApp {

    public static void main(String[] args) throws InterruptedException {

        // ─── 1. Setup lot ───────────────────────────────────────────────
        ParkingLot parkingLot = ParkingLot.getInstance();

        parkingLot.addFloor(new ParkingFloor("01", List.of(
                new ParkingSpot(VehicleType.CAR),
                new ParkingSpot(VehicleType.CAR),
                new ParkingSpot(VehicleType.BIKE),
                new ParkingSpot(VehicleType.TRUCK)
        )));

        parkingLot.addFloor(new ParkingFloor("02", List.of(
                new ParkingSpot(VehicleType.CAR),
                new ParkingSpot(VehicleType.BIKE),
                new ParkingSpot(VehicleType.TRUCK)
        )));

        // ─── 2. Register observer ────────────────────────────────────────
        parkingLot.addObserver(new DisplayObserver("MainEntrance"));

        // Show initial state
        System.out.println("=== Initial Lot State ===");
        parkingLot.notifyObservers();

        // ─── 3. Wire service ─────────────────────────────────────────────
        ParkingLotService service = new ParkingLotService(
                parkingLot,
                new NearestSpotAssignmentStrategy(parkingLot),
                new CreditCardPayment("102745275986", "Somnath Mukherjee"),
                new HourlyPricingStrategy(60.00)
        );

        // ─── 4. Park multiple vehicle types ──────────────────────────────
        System.out.println("\n=== Parking vehicles ===");

        Vehicle car1 = new Vehicle("KA01AB1234", VehicleType.CAR);
        Vehicle bike1 = new Vehicle("KA02CD5678", VehicleType.BIKE);
        Vehicle truck1 = new Vehicle("KA03EF9012", VehicleType.TRUCK);
        Vehicle car2 = new Vehicle("WB04GH3456", VehicleType.CAR);

        Ticket t1 = service.park(car1);
        Ticket t2 = service.park(bike1);
        Ticket t3 = service.park(truck1);
        Ticket t4 = service.park(car2);

        // ─── 5. Simulate duration ─────────────────────────────────────────
        System.out.println("\n[Simulating 2 seconds of parking time...]");
        Thread.sleep(2000);

        // ─── 6. Unpark and pay ────────────────────────────────────────────
        System.out.println("\n=== Unparking car1 ===");
        double fee1 = service.unPark(t1);
        service.pay(fee1);

        System.out.println("\n=== Unparking bike1 — switching to cash payment ===");
        service.switchPaymentStrategy(new CashPayment());
        double fee2 = service.unPark(t2);
        service.pay(fee2);

        // ─── 7. No spot available — expect exception ──────────────────────
        System.out.println("\n=== Attempting to park 3 more trucks (only 1 truck spot left) ===");
        Vehicle truck2 = new Vehicle("MH05IJ7890", VehicleType.TRUCK);
        Vehicle truck3 = new Vehicle("DL06KL1234", VehicleType.TRUCK);

        service.park(truck2); // should succeed — one truck spot on floor 2

        try {
            service.park(truck3); // should throw — no truck spots left
        } catch (RuntimeException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // ─── 8. Unpark remaining ─────────────────────────────────────────
        System.out.println("\n=== Clearing remaining vehicles ===");
        service.unPark(t3);
        service.unPark(t4);

        System.out.println("\n=== Final Lot State ===");
        parkingLot.notifyObservers();
    }
}