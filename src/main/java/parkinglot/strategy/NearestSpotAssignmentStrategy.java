package parkinglot.strategy;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;
import parkinglot.models.ParkingSpot;

import java.util.List;
import java.util.Map;

public class NearestSpotAssignmentStrategy implements ParkingSpotAssignmentStrategy {
    private Map<String, List<ParkingSpot>> availableSpots;

    public NearestSpotAssignmentStrategy(Map<String, List<ParkingSpot>> availableSpots) {
        this.availableSpots = availableSpots;
    }

    @Override
    public ParkingSpot getParkingSpot(VehicleType vehicleType) {
        return availableSpots.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .filter(spot -> spot.getVehicleType().equals(vehicleType) && spot.getSlotStatus().equals(SlotStatus.VACANT))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No Parking Spot is available at the moment for the vehicle type : " + vehicleType));
    }
}
