package parkinglot.models;

import parkinglot.enums.VehicleType;

import java.util.UUID;

public class Vehicle {
    private final String id;
    private final String plateNumber;
    private final VehicleType vehicleType;

    public Vehicle(String plateNumber, VehicleType vehicleType) {
        this.id = UUID.randomUUID().toString();
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
    }

    public String getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
