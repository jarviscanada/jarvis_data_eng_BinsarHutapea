package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Suite.SuiteClasses({ValidParentheses.class})
public class ValidParenthesesTest {

    @Test
    public void testParenthesesValidation(){

        ValidParentheses validParentheses = new ValidParentheses();

        String caseOneString = "()";
        String caseTwoString = "({[]})";
        String caseThreeString = "({[]))";

        assertTrue(validParentheses.isValid(caseOneString));
        assertTrue(validParentheses.isValid(caseTwoString));
        assertFalse(validParentheses.isValid(caseThreeString));



    }

}
