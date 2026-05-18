package selfwritten.stack;

import org.junit.jupiter.api.Test;

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
}
