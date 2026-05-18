package selfwritten.stack;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class StackProblems {
    private static final List<Character> openParentheses = List.of('{', '[', '(');
    private static final List<Character> closeParentheses = List.of('}', ']', ')');
    private static final Map<Character, Character> parenthesesMap =
            Map.of('}', '{', ']', '[', ')', '(');

    public boolean validParenthesis(String str){
        Stack<Character> stack = new Stack<>();
        for (char c: str.toCharArray()){
            if (openParentheses.contains(c)){
                stack.push(c);
            } else if (closeParentheses.contains(c)){
                if (!stack.isEmpty()) {
                    var topElement = stack.pop();
                    if (!topElement.equals(parenthesesMap.get(c))) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
