package parkinglot.strategy;

import parkinglot.enums.VehicleType;
import parkinglot.models.ParkingSpot;

public interface ParkingSpotAssignmentStrategy {
    ParkingSpot getParkingSpot(VehicleType vehicleType);
}
