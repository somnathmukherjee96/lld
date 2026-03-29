package parkinglot.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingLot {
    private static ParkingLot instance;

    private final String id;
    private List<ParkingFloor> parkingFloors;

    private ParkingLot() {
        this.id = UUID.randomUUID().toString();
        this.parkingFloors = new ArrayList<>();
    }

    public static ParkingLot  getInstance(){
        if(instance==null)
            instance = new ParkingLot();
        return instance;
    }

    public String getId() {
        return id;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public boolean addFloor(ParkingFloor floor) {
        return this.parkingFloors.add(floor);
    }

}
