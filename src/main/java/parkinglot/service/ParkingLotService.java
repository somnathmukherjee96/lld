package parkinglot.service;

import parkinglot.models.*;
import parkinglot.strategy.ParkingSpotAssignmentStrategy;
import parkinglot.strategy.PaymentStrategy;
import parkinglot.strategy.PricingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingLotService {
    private final ParkingLot parkingLot;
    private final ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy;
    private PaymentStrategy paymentStrategy;
    private final PricingStrategy pricingStrategy;

    public ParkingLotService(ParkingLot parkingLot, ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy, PaymentStrategy paymentStrategy, PricingStrategy pricingStrategy) {
        this.parkingLot = parkingLot;
        this.parkingSpotAssignmentStrategy = parkingSpotAssignmentStrategy;
        this.paymentStrategy = paymentStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket park(Vehicle vehicle) {
        ParkingSpot spot = parkingSpotAssignmentStrategy.getParkingSpot(vehicle.getVehicleType());
        spot.assignVehicle(vehicle);
        parkingLot.notifyObservers();
        return new Ticket(vehicle, spot);
    }

    public double unPark(Ticket ticket) {
        ticket.getParkingSpot().removeVehicle();
        parkingLot.notifyObservers();
        return pricingStrategy.calculateFee(Duration.between(ticket.getEntryTimeStamp(), LocalDateTime.now()).toMinutes());
    }

    public boolean pay(double amount) {
        paymentStrategy.pay(amount);
        return true;
    }

    public void switchPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}
