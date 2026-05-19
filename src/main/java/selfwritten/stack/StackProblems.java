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

    public String decodeString(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c: str.toCharArray()) {
            stack.push(c);
        }
        StringBuilder mainStringBuilder = new StringBuilder();
        StringBuilder subStringBuilder = new StringBuilder();
        while (!stack.isEmpty()){
            var c = stack.pop();
            if (closeParentheses.contains(c)){
                mainStringBuilder.append(subStringBuilder.toString().trim());
                subStringBuilder = new StringBuilder();
            } else if (openParentheses.contains(c)){
                continue;
            } else if(Character.isDigit(c)){
                var subString = subStringBuilder.toString().trim();
                subStringBuilder = new StringBuilder();

                StringBuilder counterSb = new StringBuilder();
                counterSb.append(c);
                while(!stack.isEmpty() && Character.isDigit(stack.peek())){
                    counterSb.append(stack.pop());
                }
                var multiplier = counterSb.reverse().toString().trim();


                for (int i=1; i<=Integer.parseInt(multiplier); i++){
                    subStringBuilder.append(subString);
                }
            } else {
                subStringBuilder.append(c);
            }
        }
        mainStringBuilder.append(subStringBuilder.toString().trim());
       return mainStringBuilder.reverse().toString().trim();
    }
}
