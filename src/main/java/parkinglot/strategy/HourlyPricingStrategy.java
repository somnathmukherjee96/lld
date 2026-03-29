package parkinglot.strategy;

public class HourlyPricingStrategy implements PricingStrategy {
    private double hourlyRate;

    public HourlyPricingStrategy(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateFee(long minutes) {
        long hours = Math.max(1, (long) Math.ceil(minutes / 60.0));
        return hourlyRate * hours;
    }
}
