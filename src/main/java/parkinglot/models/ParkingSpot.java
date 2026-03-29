package parkinglot.models;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;

import java.util.UUID;

public class ParkingSpot {
    private final String id;
    private final VehicleType supportedType;
    private Vehicle assignedVehicle;
    private SlotStatus slotStatus;

    public ParkingSpot(VehicleType supportedType) {
        this.id = UUID.randomUUID().toString();
        this.supportedType = supportedType;
        this.slotStatus = SlotStatus.VACANT;
    }

    public String getId() {
        return id;
    }

    public VehicleType getSupportedType() {
        return supportedType;
    }

    public Vehicle getAssignedVehicle() {
        return assignedVehicle;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    public boolean isVacant() {
        return this.slotStatus == SlotStatus.VACANT;
    }

    public synchronized void assignVehicle(Vehicle vehicle) {
        if (vehicle.getVehicleType() != supportedType)
            throw new IllegalArgumentException(
                    "Spot supports " + supportedType + ", got " + vehicle.getVehicleType());
        if (!isVacant())
            throw new IllegalStateException("Spot " + id + " is already occupied");
        this.assignedVehicle = vehicle;
        this.slotStatus = SlotStatus.OCCUPIED;
    }

    public synchronized void removeVehicle() {
        if (this.assignedVehicle == null)
            throw new IllegalArgumentException("No Vehicle parked here!");

        this.assignedVehicle = null;
        this.slotStatus = SlotStatus.VACANT;
    }
}
