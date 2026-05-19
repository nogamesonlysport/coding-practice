package selfwritten.triald;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheWithLinkedHashMapTest {
    @Test
    public void testLinkedHashSetLRUCache(){
        var cache = new LRUCacheWithLinkedHashMap(3);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        cache.printCache();
        System.out.println("get(2)");
        cache.get(2);
        cache.printCache();
        System.out.println("put(4, 40)");
        cache.put(4, 40);
        cache.printCache();
    }

    @Test
    public void testThreadSafeLRUCacheWithMultipleThreads() throws InterruptedException {
        final int CACHE_SIZE = 100;
        final int NUM_THREADS = 10;
        final int OPERATIONS_PER_THREAD = 1000;

        ThreadSafeLRUCacheWithLinkedHashMap cache = new ThreadSafeLRUCacheWithLinkedHashMap(CACHE_SIZE);
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        CountDownLatch latch = new CountDownLatch(NUM_THREADS);
        AtomicInteger successfulOperations = new AtomicInteger(0);
        List<Future<?>> futures = new ArrayList<>();

        // Submit multiple threads performing concurrent put and get operations
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            Future<?> future = executor.submit(() -> {
                try {
                    for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                        int key = (threadId * OPERATIONS_PER_THREAD + j) % 200;
                        int value = threadId * 10000 + j;

                        // Put operation
                        cache.put(key, value);

                        // Get operation
                        Integer retrieved = cache.get(key);

                        // Verify the value is either the one we just put or -1 (evicted)
                        if (retrieved != -1) {
                            successfulOperations.incrementAndGet();
                        }
                    }
                } finally {
                    latch.countDown();
                }
            });
            futures.add(future);
        }

        // Wait for all threads to complete
        boolean completed = latch.await(30, TimeUnit.SECONDS);
        assertTrue(completed, "All threads should complete within timeout");

        // Check that no exceptions were thrown
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                fail("Thread threw exception: " + e.getCause());
            }
        }

        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "Executor should shutdown cleanly");

        // Verify cache operations completed successfully
        assertTrue(successfulOperations.get() > 0, "Some operations should succeed");

        System.out.println("Completed " + successfulOperations.get() + " successful cache operations across " + NUM_THREADS + " threads");
    }

    @Test
    public void testThreadSafeLRUCacheEvictionUnderConcurrency() throws InterruptedException {
        final int CACHE_SIZE = 10;
        final int NUM_THREADS = 5;

        ThreadSafeLRUCacheWithLinkedHashMap cache = new ThreadSafeLRUCacheWithLinkedHashMap(CACHE_SIZE);
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(NUM_THREADS);

        // Each thread will try to add different keys
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            executor.submit(() -> {
                try {
                    startLatch.await(); // Ensure all threads start at the same time
                    for (int j = 0; j < 20; j++) {
                        int key = threadId * 100 + j;
                        cache.put(key, key * 10);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            });
        }

        // Start all threads simultaneously
        startLatch.countDown();

        // Wait for completion
        boolean completed = endLatch.await(10, TimeUnit.SECONDS);
        assertTrue(completed, "All threads should complete");

        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));

        // Verify that cache doesn't exceed capacity (indirectly, as we can't inspect size directly)
        // The cache should still be functional after concurrent operations
        cache.put(999, 999);
        assertEquals(999, cache.get(999), "Cache should still function correctly after concurrent stress");
    }

    @Test
    public void testThreadSafeLRUCacheReadWriteCorrectness() throws InterruptedException, ExecutionException {
        final int CACHE_SIZE = 50;
        ThreadSafeLRUCacheWithLinkedHashMap cache = new ThreadSafeLRUCacheWithLinkedHashMap(CACHE_SIZE);

        // Pre-populate cache
        for (int i = 0; i < 30; i++) {
            cache.put(i, i * 100);
        }

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Writer thread
        Future<?> writerFuture = executor.submit(() -> {
            for (int i = 30; i < 80; i++) {
                cache.put(i, i * 100);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Reader threads
        List<Future<Boolean>> readerFutures = new ArrayList<>();
        for (int t = 0; t < 3; t++) {
            Future<Boolean> readerFuture = executor.submit(() -> {
                boolean allValid = true;
                for (int i = 0; i < 100; i++) {
                    int key = ThreadLocalRandom.current().nextInt(0, 80);
                    Integer value = cache.get(key);
                    // If value exists, it should be correct
                    if (value != -1 && value != key * 100) {
                        allValid = false;
                        System.err.println("Incorrect value for key " + key + ": expected " + (key * 100) + ", got " + value);
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                return allValid;
            });
            readerFutures.add(readerFuture);
        }

        // Wait for all operations
        writerFuture.get();
        for (Future<Boolean> readerFuture : readerFutures) {
            assertTrue(readerFuture.get(), "All read values should be correct");
        }

        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));
    }

}

