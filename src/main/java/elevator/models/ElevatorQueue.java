package elevator.models;

import java.util.Collections;
import java.util.PriorityQueue;

public class ElevatorQueue {
    private final PriorityQueue<Floor> upQueue;
    private final PriorityQueue<Floor> downQueue;

    public ElevatorQueue() {
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>(Collections.reverseOrder());
    }

    public PriorityQueue<Floor> getUpQueue() {
        return upQueue;
    }

    public PriorityQueue<Floor> getDownQueue() {
        return downQueue;
    }

    public void addToUpQueue(Floor floor) {
        upQueue.offer(floor);
    }

    public void addToDownQueue(Floor floor) {
        downQueue.offer(floor);
    }
}
