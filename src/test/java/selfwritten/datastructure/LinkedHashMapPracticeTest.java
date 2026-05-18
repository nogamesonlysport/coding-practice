package selfwritten.datastructure;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedHashMapPracticeTest {

    @Test
    public void testLinkedHasMapApi(){
        var initialCapacity = 3;
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(initialCapacity, 0.75f, true){
          @Override
          protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
              return this.size()>initialCapacity;
          }
        };

        linkedHashMap.put(1, "a");
        linkedHashMap.put(2, "b");
        linkedHashMap.put(3, "c");
        System.out.println(linkedHashMap.keySet());

        linkedHashMap.get(2);
        System.out.println("get(2)");
        System.out.println(linkedHashMap.keySet());

        linkedHashMap.get(1);
        System.out.println("get(1)");
        System.out.println(linkedHashMap.keySet());

        linkedHashMap.put(4, "d");
        System.out.println("put(4, \"d\")");
        System.out.println(linkedHashMap.keySet());

        linkedHashMap.put(4, "d");
        System.out.println("duplicate - put(4, \"d\")");
        System.out.println(linkedHashMap.keySet());
    }


    @Test
    public void testHasMapApi(){
        var initialCapacity = 3;
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        System.out.println(map.keySet());

        map.get(2);
        System.out.println("get(2)");
        System.out.println(map.keySet());

        map.get(1);
        System.out.println("get(1)");
        System.out.println(map.keySet());

        map.put(4, "d");
        System.out.println("put(4, \"d\")");
        System.out.println(map.keySet());
    }
}
