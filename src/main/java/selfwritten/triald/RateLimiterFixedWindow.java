package selfwritten.triald;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimiterFixedWindow {
    private final int maxWindowCount;
    private final long windowLengthInMillis;
    private final Map<String, Window> windowMap = new HashMap<>();

    public boolean allowRequest(String client) {
        var clientWindow = windowMap.computeIfAbsent(client, k -> new Window());

        var now = System.currentTimeMillis();
        if (now - clientWindow.windowStartTimeInMillis.get() >= windowLengthInMillis) {
            windowMap.put(client, new Window(new AtomicInteger(1)));
            return true;
        } else if (clientWindow.windowCount.get() < maxWindowCount){
            clientWindow.windowCount.incrementAndGet();
            return true;
        }
        return false;
    }

    public RateLimiterFixedWindow(int maxWindowCount, long windowLengthInMillis) {
        this.maxWindowCount = maxWindowCount;
        this.windowLengthInMillis = windowLengthInMillis;
    }

    private class Window {
        private AtomicInteger windowCount = new AtomicInteger(0);
        private AtomicLong windowStartTimeInMillis = new AtomicLong(System.currentTimeMillis());

        public Window() {
        }

        public Window(AtomicInteger windowCount) {
            this.windowCount = windowCount;
        }
    }
}
