package generated;

import selfwritten.CacheImplementationWithoutCollections;

public class CacheImplementationWithoutCollectionsTest {
    public void testPutAndGet() {
        int capacity = 5;
        Object[] storage = new Object[capacity];
        CacheImplementationWithoutCollections cache = new CacheImplementationWithoutCollections(storage, capacity);

        cache.put("key1", "value1");
        Object result = cache.get("key1");
        assert "value1".equals(result) : "Expected 'value1' but got " + result;

        cache.put("key2", "value2");
        result = cache.get("key2");
        assert "value2".equals(result) : "Expected 'value2' but got " + result;
    }

    public void testHashCollision() {
        // Find two strings that might collide or just test with more entries than capacity
        int capacity = 2;
        Object[] storage = new Object[capacity];
        CacheImplementationWithoutCollections cache = new CacheImplementationWithoutCollections(storage, capacity);

        cache.put("key1", "value1");
        cache.put("key3", "value3"); // May collide with key1 depending on hashCode % 2

        // Since it's a very simple cache, collision results in overwrite.
        // We just verify it doesn't crash.
    }

    public void testNegativeHashCode() {
        int capacity = 10;
        Object[] storage = new Object[capacity];
        CacheImplementationWithoutCollections cache = new CacheImplementationWithoutCollections(storage, capacity);

        // Try to find a string with negative hashCode.
        String negativeHashString = null;
        for (int i = 0; i < 1000; i++) {
            String s = "test" + i;
            if (s.hashCode() < 0) {
                negativeHashString = s;
                break;
            }
        }
        
        if (negativeHashString == null) {
            System.out.println("[DEBUG_LOG] Could not find a negative hashCode string in 1000 attempts.");
            return;
        }

        System.out.println("[DEBUG_LOG] Found string with negative hashCode: '" + negativeHashString + "' (" + negativeHashString.hashCode() + ")");
        try {
            cache.put(negativeHashString, "negative");
            Object result = cache.get(negativeHashString);
            assert "negative".equals(result);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[DEBUG_LOG] Caught expected ArrayIndexOutOfBoundsException due to negative hashCode: " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
        CacheImplementationWithoutCollectionsTest test = new CacheImplementationWithoutCollectionsTest();
        
        System.out.println("Running testPutAndGet...");
        test.testPutAndGet();
        
        System.out.println("Running testHashCollision...");
        test.testHashCollision();

        System.out.println("Running testNegativeHashCode...");
        test.testNegativeHashCode();

        System.out.println("CacheImplementationWithoutCollectionsTest completed!");
    }
}
