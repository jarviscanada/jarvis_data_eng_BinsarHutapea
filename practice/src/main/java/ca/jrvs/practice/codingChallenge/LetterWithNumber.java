package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Print-letter-with-number-718d091698854cbdb060b071ad71a47d
 */
public class LetterWithNumber {

    /**
     * O(n)
     * Justification: There is a for loop that iterates through the entire input string of length n.
     * @param string
     * @return
     */
    public String printLetterWithNumber(String string){

        StringBuilder stringBuilder = new StringBuilder();

        for (Character character : string.toCharArray()){
            int number = character - '0' - '0';

            stringBuilder.append(character);
            stringBuilder.append(number);
        }

        return stringBuilder.toString();
    }

}
