package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Suite.SuiteClasses({OnlyDigitsString.class})
public class OnlyDigitsStringTest {

    @Test
    public void testOnlyDigits(){

        OnlyDigitsString onlyDigitsString = new OnlyDigitsString();

        String caseOne = "1";
        boolean caseOneResult = onlyDigitsString.onlyDigits(caseOne);

        assertTrue(caseOneResult);

        String caseTwo = "a";
        boolean caseTwoResult = onlyDigitsString.onlyDigits(caseTwo);

        assertFalse(caseTwoResult);

        String caseThree = "123456";
        boolean caseThreeResult = onlyDigitsString.onlyDigits(caseThree);

        assertTrue(caseThreeResult);

        String caseFour = "123,456";
        boolean caseFourResult = onlyDigitsString.onlyDigits(caseFour);

        assertFalse(caseFourResult);


    }

}
