package selfwritten.triald;

import java.util.LinkedHashMap;
import java.util.Map;

public class ThreadSafeLRUCacheWithLinkedHashMap {
    private final Map<Integer, Integer> cache;

    public ThreadSafeLRUCacheWithLinkedHashMap(int initialCapacity) {
        this.cache = new LinkedHashMap<>(initialCapacity, 0.75f, true){
            @Override
            public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
                return size() > initialCapacity;
            }
        };
    }

    public Integer get(int key){
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value){
        cache.put(key, value);
    }

        public void printCache() {
        System.out.println("LRU Cache (LRU -> MRU)");
        cache.forEach((k,v) -> System.out.println("[" + k + "=" + v + "] "));
        System.out.println();
    }
}
