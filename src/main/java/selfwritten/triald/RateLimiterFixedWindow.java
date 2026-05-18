package selfwritten.triald;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimiterFixedWindow {
    private int maxRequests;
    private long windowSizeInMs;
    private ConcurrentHashMap<String, Window> clientWindows = new ConcurrentHashMap<>();

    public RateLimiterFixedWindow(int maxRequests, long windowSizeInMs) {
        this.maxRequests = maxRequests;
        this.windowSizeInMs = windowSizeInMs;
    }

    private static class Window{
        private AtomicInteger counter = new AtomicInteger(0);
        private AtomicLong windowStartTimestamp = new AtomicLong(System.currentTimeMillis());
    }

    public boolean allowRequest(String clientId) {
        Window clientWindow = clientWindows.computeIfAbsent(clientId, k -> new Window());

        //if the current timestamp is beyond the client window, reset the window
        long now = System.currentTimeMillis();
        if (now - clientWindow.windowStartTimestamp.get() >= windowSizeInMs) {
            clientWindow = new Window();
        }
        var currentCount = clientWindow.counter.get();
        if (currentCount >= maxRequests) {
            return false;
        }
        currentCount = clientWindow.counter.incrementAndGet();
        return currentCount <= maxRequests;
    }
}
