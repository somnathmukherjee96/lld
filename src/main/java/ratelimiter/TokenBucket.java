package ratelimiter;

public class TokenBucket {
    private double tokens;
    private long lastRefillTimestampNanos;
    private final RateLimiterConfig rateLimiterConfig;

    public TokenBucket(RateLimiterConfig rateLimiterConfig) {
        this.rateLimiterConfig = rateLimiterConfig;
        this.tokens = rateLimiterConfig.getCapacity();
        this.lastRefillTimestampNanos = System.nanoTime();
    }

    public boolean tryConsume() {
        refill();

        if (tokens >= 1.0) {
            tokens--;
            return true;
        }
        return false;
    }

    private void refill() {
        //check last refill timestamp
        long now = System.nanoTime();
        //get the nano diff
        double elapsedSeconds = (now - lastRefillTimestampNanos) / 1_000_000_000.00;
        //calculate tokens
        double tokenToAdd = elapsedSeconds * rateLimiterConfig.getCapacity();
        tokens = Math.min(rateLimiterConfig.getCapacity(), tokens + tokenToAdd);

        lastRefillTimestampNanos = now;
    }
}
