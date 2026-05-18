package selfwritten.triald;

import org.junit.jupiter.api.Test;

public class LRUCacheWithLinkedHashMapTest {
    @Test
    public void testLinkedHashSetLRUCache(){
        var cache = new LRUCacheWithLinkedHashMap(3);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        cache.printCache();
        cache.get(2);
        cache.printCache();
        cache.put(4, 40);
        cache.printCache();
    }
}
