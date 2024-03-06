package org.example.ratelimiter;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter {

    private Duration durationOfWindow;

    private Long numberOfRequests;

    private Queue<LocalTime> localtimeQueue;

    public SlidingWindowRateLimiter(Duration durationOfWindow, Integer numberOfRequests) {
        this(durationOfWindow, numberOfRequests.longValue());
    }

    Queue<LocalTime> getLocaltimeQueue() {
        return localtimeQueue;
    }

    public SlidingWindowRateLimiter(Duration durationOfWindow, Long numberOfRequests) {
        this.durationOfWindow = durationOfWindow;
        this.numberOfRequests = numberOfRequests;
        this.localtimeQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean isRateLimited() {
        var nowTime = LocalTime.now();
        if (localtimeQueue.size() >= numberOfRequests) {
            var leastRecentAccessTime = localtimeQueue.peek();
            var differenceBetweenTimes = Duration.between(leastRecentAccessTime, nowTime);
            if (differenceBetweenTimes.compareTo(durationOfWindow) < 0) {
                // Window and Requests have exceeded.
                return false;
            } else {
                // Pop until queue size becomes lesser than size of requests.
                while ( (!localtimeQueue.isEmpty()) && (Duration.between(localtimeQueue.peek(), nowTime).getNano() > durationOfWindow.getNano()) ) {
                    localtimeQueue.remove();
                }
                localtimeQueue.add(nowTime);
                return true;
            }
        } else {
            localtimeQueue.add(nowTime);
            return true;
        }
    }
}
