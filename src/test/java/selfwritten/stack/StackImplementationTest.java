package selfwritten.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StackImplementationTest {

    @Test
    public void testStackImplementation(){
        StackImplementation stash = new StackImplementation();

        stash.push('a');
        assertEquals('a', stash.peek());

        stash.push('b');
        assertEquals('b', stash.peek());
        assertEquals('b', stash.pop());

        assertEquals('a', stash.peek());
        assertEquals('a', stash.pop());

        assertNull(stash.peek());
        assertNull(stash.pop());
    }

    @Test
    public void testStackThreadSafety() throws InterruptedException {
        StackImplementation stack = new StackImplementation();
        int numThreads = 10;
        int operationsPerThread = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);
        AtomicInteger pushCount = new AtomicInteger(0);
        List<Exception> exceptions = new ArrayList<>();

        // Create threads that push and pop concurrently
        for (int i = 0; i < numThreads; i++) {
            final char threadChar = (char) ('a' + (i % 26));
            executor.submit(() -> {
                try {
                    for (int j = 0; j < operationsPerThread; j++) {
                        stack.push(threadChar);
                        pushCount.incrementAndGet();

                        // Occasionally pop
                        if (j % 3 == 0) {
                            stack.pop();
                            pushCount.decrementAndGet();
                        }
                    }
                } catch (Exception e) {
                    synchronized (exceptions) {
                        exceptions.add(e);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(30, TimeUnit.SECONDS);
        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        // If there are exceptions, the stack is likely not thread-safe
        if (!exceptions.isEmpty()) {
            System.out.println("Thread safety issues detected:");
            exceptions.forEach(e -> e.printStackTrace());
        }

        // Verify final state - pop all remaining elements
        int actualCount = 0;
        while (stack.pop() != null) {
            actualCount++;
        }

        int expectedCount = pushCount.get();
        System.out.println("Expected elements in stack: " + expectedCount);
        System.out.println("Actual elements in stack: " + actualCount);
        System.out.println("Thread safety verdict: " +
            (actualCount == expectedCount && exceptions.isEmpty()
                ? "THREAD-SAFE"
                : "NOT THREAD-SAFE"));

        // This assertion will likely fail if the stack is not thread-safe
        assertEquals(expectedCount, actualCount,
            "Stack size mismatch indicates race conditions");
    }

}
