package selfwritten.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackProblemsTest {
    private StackProblems stack = new StackProblems();
    @Test
    public void testValidParentheses(){
        assertTrue(stack.validParenthesis("(){({})}"));
        assertFalse((stack.validParenthesis("(){({}})")));
        assertFalse((stack.validParenthesis("([)]")));
    }

    @Test
    public void testDecodeString(){
        assertEquals("aaabaaab", stack.decodeString("2[3[a]b]"));
        assertEquals("aaaaaaaaaa", stack.decodeString("10[a]"));
        assertEquals("aaabcbc", stack.decodeString("3[a]2[bc]"));
        assertEquals("accaccacc", stack.decodeString("3[a2[c]]"));
    }
}
