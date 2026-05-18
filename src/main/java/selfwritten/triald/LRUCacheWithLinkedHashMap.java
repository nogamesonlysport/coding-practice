package selfwritten.triald;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheWithLinkedHashMap {
    private final LinkedHashMap<Integer, Integer> cache;

    public LRUCacheWithLinkedHashMap(int capacity) {
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    public void printCache() {
        System.out.println("LRU Cache (MRU -> LRU)");
        cache.forEach((k,v) -> System.out.println("[" + k + "=" + v + "] "));
        System.out.println();
    }
}
