package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Parentheses-072701caa3d841a4b47272e26de360b0
 */
public class ValidParentheses {

    /**
     * O(n)
     * Justification: It uses a for-loop to iterate through the entire input string
     * @param string
     * @return a boolean value
     */
    public boolean isValid(String string){
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();

        for (Character character : string.toCharArray()){
            if (map.containsKey(character))
                stack.push(character);
            else{
                if (stack.isEmpty())
                    return false;
                char open = stack.pop();

                if (map.get(open) != character)
                    return false;
            }
        }

        return stack.isEmpty();
    }

}
