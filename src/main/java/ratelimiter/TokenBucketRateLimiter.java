package ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {
    private final Map<String, TokenBucket> buckets;
    private final RateLimiterConfig rateLimiterConfig;

    public TokenBucketRateLimiter(RateLimiterConfig rateLimiterConfig) {
        this.buckets = new ConcurrentHashMap<>();
        this.rateLimiterConfig = rateLimiterConfig;
    }

    @Override
    public boolean allowRequest(String userId) {
        TokenBucket bucket = buckets.computeIfAbsent(userId, id -> new TokenBucket(rateLimiterConfig));

        synchronized (bucket) {
            return bucket.tryConsume();
        }
    }
}
