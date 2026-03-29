package parkinglot.observer;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;
import parkinglot.models.ParkingSpot;

import java.util.List;
import java.util.Map;

public class DisplayObserver implements ParkingLotObserver {
    private final String id;

    public DisplayObserver(String id) {
        this.id = id;
    }

    @Override
    public void update(Map<String, List<ParkingSpot>> floorData) {
        System.out.println("======DisplayBoard [" + id + "]======");
        for(String floor:floorData.keySet()){
            System.out.println("Floor: "+floor);
            System.out.println("----------------------------------");
            List<ParkingSpot> vacantSpots = floorData.get(floor).stream()
                    .filter(spot -> spot.getSlotStatus().equals(SlotStatus.VACANT))
                    .toList();
            long carSpotCount = vacantSpots.stream()
                    .filter(spot -> spot.getVehicleType().equals(VehicleType.CAR))
                    .count();
            long bikeSpotCount = vacantSpots.stream()
                    .filter(spot -> spot.getVehicleType().equals(VehicleType.BIKE))
                    .count();
            long truckSpotCount = vacantSpots.stream()
                    .filter(spot -> spot.getVehicleType().equals(VehicleType.TRUCK))
                    .count();
            System.out.println("CAR -> "+carSpotCount);
            System.out.println("BIKE -> "+bikeSpotCount);
            System.out.println("TRUCK -> "+truckSpotCount);
            System.out.println("----------------------------------");
        }
        System.out.println("===============================================");
    }
}
