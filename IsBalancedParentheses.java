package test.datastructures.stack;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class IsBalancedParentheses {
    private static final Map<Character, Character> parentheses = new HashMap<>();
    static {
        parentheses.put('(',')');
        parentheses.put('{','}');
        parentheses.put('[',']');
        parentheses.put(')','(');
        parentheses.put('}','{');
        parentheses.put(']','[');
    }

    public static void main(String... asd) {
        String s1 = "((({}[()])))";
        String s2 = "{()}[";
        boolean correct = isCorrect(s1);
        System.out.println(s1 + " correct = " + correct);
        boolean correct1 = isCorrect(s2);
        System.out.println(s2 + " correct1 = " + correct1);
    }

    static boolean isCorrect(String expression) {
        char[] chars = expression.toCharArray();
        ArrayDeque<Character> parenthesesStack = new ArrayDeque<>();
        for (Character character : chars) {
            switch (character) {
                case '{' :
                case '(' :
                case '[' :
                    parenthesesStack.addLast(character);
                    break;
                case ')' :
                case '}' :
                case ']' :
                    Character element = parenthesesStack.pollLast();
                    Character oppositeElement = parentheses.get(element);
                    if (!character.equals(oppositeElement)) {
                        return false;
                    }
                    break;
            }
        }
        return parenthesesStack.isEmpty();
    }
}
