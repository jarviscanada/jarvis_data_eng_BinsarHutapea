package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-1b159848773d41b8a98c1cf3265b4f1b
 */
public class ValidPalindrome {

    /**
     * O(n)
     * Justification: during a while loop, two pointers from both ends of the string converge on each other
     * @param text input text
     * @return true if the text is Palindrome, false if it is not
     */
    public boolean validatePalindrome(String text){

        text = text.toLowerCase();
        int left = 0;
        int right = text.length()-1;

        while( left < right ){

            Character currentLeftChar = text.charAt(left);
            Character currentRightChar = text.charAt(right);

            if (!Character.isLetterOrDigit(currentLeftChar))
                left++;
            else if (!Character.isLetterOrDigit(currentRightChar))
                right--;
            else if (currentLeftChar == currentRightChar){
                left++;
                right--;
            }
            else
                return false;
        }
        return true;
    }

    /**
     * O(n)
     * Justification: it is the time complexity of the validatePalindromeWithRecursion method that is called by this method
     * @param text input text
     * @return true if the text is Palindrome, false if it is not
     */
    public boolean validatePalindromeWithRecursionMain(String text){

        text = text.toLowerCase();

        return validatePalindromeWithRecursion(text, 0, text.length()-1);
    }

    /**
     * O(n)
     * Justification: On every recursive call, two pointers on both ends of the input text converge on each other
     * @param text input text
     * @return true if the text is Palindrome, false if it is not
     */
    public boolean validatePalindromeWithRecursion(String text, int left, int right){

        if (left >= right)
            return true;

        if (!Character.isLetterOrDigit(text.charAt(left)))
            return validatePalindromeWithRecursion(text, left+1, right);

        else if (!Character.isLetterOrDigit(text.charAt(right)))
            return validatePalindromeWithRecursion(text, left, right-1);

        else if (text.charAt(left) == text.charAt(right))
            return validatePalindromeWithRecursion(text, left+1, right-1);

        else
            return false;

    }
}
