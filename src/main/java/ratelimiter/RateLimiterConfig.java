package ratelimiter;

public class RateLimiterConfig {
    private final int capacity;
    private final double refillRatePerSecond;

    public RateLimiterConfig(int capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRefillRatePerSecond() {
        return refillRatePerSecond;
    }
}
