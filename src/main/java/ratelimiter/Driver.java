package ratelimiter;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterConfig rateLimiterConfig = new RateLimiterConfig(5, 2.0);
        RateLimiter rateLimiter = new TokenBucketRateLimiter(rateLimiterConfig);

        String userId = "user_123";

        for (int i = 1; i <= 7; i++) {
            System.out.println("Request " + i + ": " +
                    ((rateLimiter.allowRequest(userId))
                            ? "ALLOWED" : "REJECTED"));
        }

        Thread.sleep(1000);
        System.out.println("After 1s refill");
        for (int i = 1; i <= 7; i++) {
            System.out.println("Request " + i + ": " +
                    ((rateLimiter.allowRequest(userId))
                            ? "ALLOWED" : "REJECTED"));
        }

    }
}
