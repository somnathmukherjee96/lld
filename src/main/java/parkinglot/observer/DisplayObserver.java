package parkinglot.observer;

import parkinglot.enums.VehicleType;
import parkinglot.models.ParkingFloor;
import parkinglot.models.ParkingLot;
import parkinglot.models.ParkingSpot;

import java.util.List;

public class DisplayObserver implements ParkingLotObserver {
    private final String id;

    public DisplayObserver(String id) {
        this.id = id;
    }

    @Override
    public void update(ParkingLot parkingLot) {
        System.out.println("======DisplayBoard [" + id + "]======");
        for (ParkingFloor floor : parkingLot.getParkingFloors()) {
            System.out.println("Floor: " + floor.getFloorNumber());
            System.out.println("----------------------------------");
            List<ParkingSpot> vacantSpots = floor.getAvailableParkingSpots();
            long carSpotCount = vacantSpots.stream()
                    .filter(spot -> spot.getSupportedType().equals(VehicleType.CAR))
                    .count();
            long bikeSpotCount = vacantSpots.stream()
                    .filter(spot -> spot.getSupportedType().equals(VehicleType.BIKE))
                    .count();
            long truckSpotCount = vacantSpots.stream()
                    .filter(spot -> spot.getSupportedType().equals(VehicleType.TRUCK))
                    .count();
            System.out.println("CAR -> " + carSpotCount);
            System.out.println("BIKE -> " + bikeSpotCount);
            System.out.println("TRUCK -> " + truckSpotCount);
            System.out.println("----------------------------------");
        }
        System.out.println("===============================================");
    }
}
