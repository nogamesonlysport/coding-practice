package selfwritten.triald;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {

    @Test
    public void testLinkedListCreation(){
        LinkedList ll = new LinkedList();
        ll.append(1);
        ll.append(2);
        assertEquals(2, ll.size());
    }

    @Test
    public void testReversedLinkedList(){
        LinkedList ll = new LinkedList();
        ll.append(1);
        ll.append(2);
        ll.append(3);
        var reversedLL = ll.reverse();
        assertEquals(3, reversedLL.size());
    }
}
