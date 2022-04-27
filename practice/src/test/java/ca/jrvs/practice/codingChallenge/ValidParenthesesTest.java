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

        assertTrue(validParentheses.validateParentheses(caseOneString));
        assertTrue(validParentheses.validateParentheses(caseTwoString));
        assertFalse(validParentheses.validateParentheses(caseThreeString));



    }

}
