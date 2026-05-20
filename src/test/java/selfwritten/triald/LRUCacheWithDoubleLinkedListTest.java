package selfwritten.triald;

import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LRUCacheWithDoubleLinkedListTest {
    @Test
    public void testLinkedHashSetLRUCache(){
        BiConsumer<Integer, Integer> evictionListener = new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer key, Integer value) {
                System.out.println("Evicted: " + key + " -> " + value);
            }
        };
        var cache = new LRUCacheWithDoubleLinkedList(3, evictionListener);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        //cache has reached full capacity

        //moved 1 to MSU position
        assertEquals(10, cache.get(1));
        //moved 3 to MSU position
        assertEquals(30, cache.get(3));

        //adding another element should remove LRU element from the cache (2)
        cache.put(4, 40);
        assertNull(cache.get(2));
    }
}
