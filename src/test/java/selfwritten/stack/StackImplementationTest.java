package selfwritten.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StackImplementationTest {

    @Test
    public void testStackImplementation(){
        StackImplementation stack = new StackImplementation();

        stack.push('a');
        assertEquals('a', stack.peek());

        stack.push('b');
        assertEquals('b', stack.peek());
        assertEquals('b', stack.pop());

        assertEquals('a', stack.peek());
        assertEquals('a', stack.pop());
        assertNull(stack.peek());
        assertNull(stack.pop());
    }
}
