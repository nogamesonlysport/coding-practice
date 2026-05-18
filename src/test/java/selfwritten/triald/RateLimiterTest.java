package selfwritten.triald;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RateLimiterTest {

    @Test
    public void testRateLimiter_singleClient() throws InterruptedException {
        RateLimiterFixedWindow rateLimiter = new RateLimiterFixedWindow(3, 5000);
        long now = System.currentTimeMillis();
        assertTrue(rateLimiter.allowRequest("user1"));
        Thread.sleep(1000);
        assertTrue(rateLimiter.allowRequest("user1"));
        Thread.sleep(1000);
        assertTrue(rateLimiter.allowRequest("user1"));
        Thread.sleep(1000);
        assertFalse(rateLimiter.allowRequest("user1"));
        Thread.sleep(3000);
        assertTrue(rateLimiter.allowRequest("user1"));
    }

    @Test
    public void testRateLimiter_multipleClients() throws InterruptedException {
        RateLimiterFixedWindow rateLimiter = new RateLimiterFixedWindow(3, 5000);
        assertTrue(rateLimiter.allowRequest("user1"));
        Thread.sleep(1000);
        assertTrue(rateLimiter.allowRequest("user1"));
        Thread.sleep(1000);
        assertTrue(rateLimiter.allowRequest("user1"));
        Thread.sleep(1000);
        assertTrue(rateLimiter.allowRequest("user2"));
        Thread.sleep(1000);
        assertFalse(rateLimiter.allowRequest("user1"));
        Thread.sleep(1000);
        assertTrue(rateLimiter.allowRequest("user1"));
    }
}
