package elevator.models;

import elevator.enums.Button;

public class Floor implements Comparable<Floor> {
    private final int floorNumber;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public int compareTo(Floor o) {
        return Integer.compare(this.floorNumber, o.floorNumber);
    }
}
