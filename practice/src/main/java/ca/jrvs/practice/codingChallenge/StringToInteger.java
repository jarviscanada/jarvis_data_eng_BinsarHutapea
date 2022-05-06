package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-60a70fa5a3d642d882910e0b8a40eb05
 */
public class StringToInteger {

    /**
     * O(n)
     * Justification: There are a while loop and a for loop. Both of them iterate through the entire input string at most.
     * @param string
     * @return
     */
    public int convertStringToInteger(String string){

        double result = 0; //For handling overflow.

        if (string.length()==0)
            return 0;

        int index = 0;

        int multiplier = 1;

        while(index < string.length() && string.charAt(index) == ' '){
            index++;
        }

        if (index >= string.length())
            return 0;

        if (string.charAt(index) == '-'){
            multiplier = -1;
            index++;
        }
        else if (string.charAt(index) == '+'){
            index++;
        }

        if (index >= string.length())
            return 0;

        if (!Character.isDigit(string.charAt(index)))
            return 0;

        for (int i = index; i < string.length(); i++){

            if (Character.isDigit(string.charAt(i))){
                int value = (int) (string.charAt(i) - '0');
                result = result * 10 + value;
            }
            else
                break;
        }

        result = result * multiplier;

        if (result > Integer.MAX_VALUE)
            result = Integer.MAX_VALUE;
        else if (result < Integer.MIN_VALUE)
            result = Integer.MIN_VALUE;

        return (int) result;
    }
}
