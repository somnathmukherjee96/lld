package parkinglot.models;

import parkinglot.enums.SlotStatus;

import java.util.List;
import java.util.UUID;

public class ParkingFloor {
    private final String id;
    private final String floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(String floorNumber, List<ParkingSpot> parkingSpots) {
        this.id = UUID.randomUUID().toString();
        this.floorNumber = floorNumber;
        this.parkingSpots = parkingSpots;
    }

    public String getId() {
        return id;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public List<ParkingSpot> getAvailableParkingSpots() {
        return parkingSpots.stream()
                .filter(p -> p.getSlotStatus() == SlotStatus.VACANT)
                .toList();
    }

    public boolean isFloorFull() {
        return parkingSpots.stream().allMatch(p -> p.getSlotStatus() == SlotStatus.OCCUPIED);
    }
}
