package org.example.ratelimiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SlidingWindowRateLimiterTest {

    private RateLimiter rateLimiter;

    private static final Duration DURATION = Duration.ofSeconds(1);

    private static final Integer NO_OF_REQUESTS = 5;

    @BeforeEach
    public void beforeEach() {
        rateLimiter = new SlidingWindowRateLimiter(DURATION, NO_OF_REQUESTS);
    }

    @Test
    public void testAllSuccessful() {
        for (int i=0;i<5;i++) {
            assertTrue(rateLimiter.isRateLimited());
        }
    }

    @Test
    public void testWindowPlus1TFail() {
        for (int i=0;i<NO_OF_REQUESTS;i++) {
            assertTrue(rateLimiter.isRateLimited());
        }
        assertFalse(rateLimiter.isRateLimited());
        assertEquals(NO_OF_REQUESTS, ((SlidingWindowRateLimiter) rateLimiter).getLocaltimeQueue().size());
    }

    @Test
    public void testRequestInNextWindowPasses() throws InterruptedException {
        for (int i=0;i<NO_OF_REQUESTS;i++) {
            assertTrue(rateLimiter.isRateLimited());
        }
        Thread.sleep(DURATION.toMillis());
        for (int i=0;i<NO_OF_REQUESTS;i++) {
            assertTrue(rateLimiter.isRateLimited());
        }
        assertEquals(NO_OF_REQUESTS, ((SlidingWindowRateLimiter) rateLimiter).getLocaltimeQueue().size());
        assertFalse(rateLimiter.isRateLimited());
    }
}
