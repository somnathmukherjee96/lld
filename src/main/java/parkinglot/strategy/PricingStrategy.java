package parkinglot.strategy;

public interface PricingStrategy {
    double calculateFee(long minutes);
}
