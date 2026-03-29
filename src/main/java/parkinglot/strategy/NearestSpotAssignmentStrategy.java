package parkinglot.strategy;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;
import parkinglot.models.ParkingLot;
import parkinglot.models.ParkingSpot;

public class NearestSpotAssignmentStrategy implements ParkingSpotAssignmentStrategy {
    private final ParkingLot parkingLot;

    public NearestSpotAssignmentStrategy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public ParkingSpot getParkingSpot(VehicleType vehicleType) {
        return parkingLot.getParkingFloors().stream()
                .flatMap(floor -> floor.getAvailableParkingSpots().stream())
                .filter(spot -> spot.getSupportedType().equals(vehicleType) && spot.getSlotStatus().equals(SlotStatus.VACANT))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No Parking Spot is available at the moment for the vehicle type : " + vehicleType));
    }
}
