package parkinglot.models;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;

import java.util.UUID;

public class ParkingSpot {
    private final String id;
    private final VehicleType vehicleType;
    private Vehicle assignedVehicle;
    private SlotStatus slotStatus;
    private final ParkingFloor parkingFloor;

    public ParkingSpot(VehicleType vehicleType, ParkingFloor parkingFloor) {
        this.id = UUID.randomUUID().toString();
        this.vehicleType = vehicleType;
        this.parkingFloor = parkingFloor;
    }

    public String getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getAssignedVehicle() {
        return assignedVehicle;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    public void assignVehicle(Vehicle vehicle) {
        if (vehicle.getVehicleType() != vehicleType)
            throw new IllegalArgumentException("This Vehicle type is not allowed! This spot is only for vehicle type:" + vehicleType);
        this.assignedVehicle = vehicle;
    }

    public void removeVehicle() {
        if (this.assignedVehicle == null)
            throw new IllegalArgumentException("No Vehicle parked here!");

        this.assignedVehicle = null;
    }
}
