package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Anagram-d69fc2fd8bef4d1b91243d18dc2ddff7
 */
public class ValidAnagram {

    /**
     * O(n)
     * Justification: There are two separate for loops, one iterates through the first input string and the other one iterates through the other input string.
     * @param stringOne first input string
     * @param stringTwo second input string
     * @return a boolean value
     */
    public boolean validateAnagram(String stringOne, String stringTwo){

        HashMap<Character, Integer> letterMap = new HashMap<>();

        if (stringOne.length() != stringTwo.length())
            return false;

        for (int i = 0; i < stringOne.length(); i++){
            char character = stringOne.charAt(i);
            letterMap.put(character, letterMap.getOrDefault(character, 0)+1);
        }

        for (int i = 0; i < stringTwo.length(); i++){
            char character = stringTwo.charAt(i);
            if (!letterMap.containsKey(character))
                return false;
            letterMap.put(character, letterMap.get(character)-1);
            if (letterMap.get(character).equals(0))
                letterMap.remove(character);
        }

        return letterMap.isEmpty();

    }

}
