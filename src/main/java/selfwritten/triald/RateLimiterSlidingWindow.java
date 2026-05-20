package selfwritten.triald;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RateLimiterSlidingWindow {
    private int maxRequests;
    private long windowLengthInMillis;
    private Map<String, Queue<Long>> windowMap = new HashMap<>();

    public RateLimiterSlidingWindow(int maxRequests, long windowLengthInMillis) {
        this.maxRequests = maxRequests;
        this.windowLengthInMillis = windowLengthInMillis;
    }


    public boolean allowRequest(String client) {
        var clientWindow = windowMap.computeIfAbsent(client, k -> new ConcurrentLinkedQueue<>());
        var now = System.currentTimeMillis();

        while(clientWindow.peek()!=null && now - clientWindow.peek() > windowLengthInMillis) {
            clientWindow.poll();
        }

        if (clientWindow.size() < maxRequests) {
            clientWindow.offer(now);
            return true;
        }

        return false;
    }
}
