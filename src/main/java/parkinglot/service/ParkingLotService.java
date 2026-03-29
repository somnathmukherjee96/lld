package parkinglot.service;

import parkinglot.models.ParkingLot;
import parkinglot.models.ParkingSpot;
import parkinglot.models.Ticket;
import parkinglot.models.Vehicle;
import parkinglot.strategy.ParkingSpotAssignmentStrategy;
import parkinglot.strategy.PaymentStrategy;
import parkinglot.strategy.PricingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingLotService {
    private final ParkingLot parkingLot;
    private final ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy;
    private final PaymentStrategy paymentStrategy;
    private final PricingStrategy pricingStrategy;

    public ParkingLotService(ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy, PaymentStrategy paymentStrategy, PricingStrategy pricingStrategy) {
        this.parkingLot = ParkingLot.getInstance();
        this.parkingSpotAssignmentStrategy = parkingSpotAssignmentStrategy;
        this.paymentStrategy = paymentStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket park(Vehicle vehicle) {
        ParkingSpot spot = parkingSpotAssignmentStrategy.getParkingSpot(vehicle.getVehicleType());
        spot.assignVehicle(vehicle);
        return new Ticket(vehicle, spot);
    }

    public double unPark(Ticket ticket) {
        ticket.getParkingSpot().removeVehicle();
        return pricingStrategy.calculateFee(Duration.between(ticket.getEntryTimeStamp(), LocalDateTime.now()).toMinutes());
    }

    public boolean pay(double amount) {
        paymentStrategy.pay(amount);
        return true;
    }
}
