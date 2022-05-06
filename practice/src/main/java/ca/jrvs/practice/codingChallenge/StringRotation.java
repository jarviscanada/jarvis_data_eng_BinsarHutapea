package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Rotate-String-72fd0d2c470e48ba8452a2955e765cf3
 */
public class StringRotation {

    /**
     * O(n^2)
     * Justification: 'contains' method performs 'indexOf' method, which performs
     * a nested for loop to check if there is a substring of string s that matches the pattern of goal string.
     * On a positive scenario, both string s and goal string are of the same size.
     * @param s base string
     * @param goal goal string
     * @return a boolean value
     */
    public boolean rotateString(String s, String goal){

        if (s.length() != goal.length())
            return false;

        String concatenated = s + s;

        if (concatenated.contains(goal))
            return true;

        return false;

    }

}
