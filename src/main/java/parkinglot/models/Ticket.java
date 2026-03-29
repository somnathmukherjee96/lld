package parkinglot.models;

import java.util.UUID;

public class Ticket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingFloor parkingFloor;

    public Ticket(Vehicle vehicle, ParkingFloor parkingFloor) {
        this.id = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.parkingFloor = parkingFloor;
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }
}
