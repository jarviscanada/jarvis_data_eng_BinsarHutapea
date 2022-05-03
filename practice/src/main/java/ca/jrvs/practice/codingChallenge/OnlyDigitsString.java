package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-2f19ee46a5294f8eb583623b1124cab4
 */
public class OnlyDigitsString {

    /**
     * O(n)
     * Justification: There is a for loop that iterates through the entire input string of length n.
     * @return
     */
    public boolean onlyDigits(String string){

        for (Character character : string.toCharArray()){

            int ascii = character;

            if (ascii < 48 || ascii > 57)
                return false;
        }

        return true;
    }
}
