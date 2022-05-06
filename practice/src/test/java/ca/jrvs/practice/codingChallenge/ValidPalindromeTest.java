package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Suite.SuiteClasses({ValidPalindrome.class})
public class ValidPalindromeTest {

    @Test
    public void testValidatePalindrome(){

        ValidPalindrome validPalindrome = new ValidPalindrome();

        String textOne = "tenet";
        String textTwo = "flight";
        String textThree = "Ma d a m.";
        String textFour = "b-7 47b";
        boolean caseOneResult = validPalindrome.validatePalindrome(textOne);
        boolean caseTwoResult = validPalindrome.validatePalindrome(textTwo);
        boolean caseThreeResult = validPalindrome.validatePalindrome(textThree);
        boolean caseFourResult = validPalindrome.validatePalindrome(textFour);

        assertTrue(caseOneResult);
        assertFalse(caseTwoResult);
        assertTrue(caseThreeResult);
        assertTrue(caseFourResult);

    }

    @Test
    public void testValidatePalindromeWithRecursion(){

        ValidPalindrome validPalindrome = new ValidPalindrome();

        String textOne = "tenet";
        String textTwo = "flight";
        String textThree = "Ma d a m.";
        String textFour = "b-7 47b";
        boolean caseOneResult = validPalindrome.validatePalindromeWithRecursionMain(textOne);
        boolean caseTwoResult = validPalindrome.validatePalindromeWithRecursionMain(textTwo);
        boolean caseThreeResult = validPalindrome.validatePalindromeWithRecursionMain(textThree);
        boolean caseFourResult = validPalindrome.validatePalindromeWithRecursionMain(textFour);

        assertTrue(caseOneResult);
        assertFalse(caseTwoResult);
        assertTrue(caseThreeResult);
        assertTrue(caseFourResult);

    }

}
