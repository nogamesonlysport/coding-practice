package selfwritten;

public class CacheImplementationWithoutCollections {
    private final Object[] cache;
    private final int capacity;

    public CacheImplementationWithoutCollections(Object[] cache, int capacity) {
        this.cache = cache;
        this.capacity = capacity;
    }

    public void put(String key, Object value) {
        var index = Math.abs(key.hashCode()) % capacity;
        cache[index] = value;
    }

    public Object get(String key) {
        var index = Math.abs(key.hashCode()) % capacity;
        return cache[index];
    }
}
