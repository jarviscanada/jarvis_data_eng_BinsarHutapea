package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({StringToInteger.class})
public class StringToIntegerTest {

    @Test
    public void testConvertStringToInteger(){

        StringToInteger stringToInteger = new StringToInteger();

        String caseOneString = "";
        int expectedCaseOneResult = 0;

        int actualCaseOneResult = stringToInteger.convertStringToInteger(caseOneString);

        assertEquals(expectedCaseOneResult, actualCaseOneResult);

        String caseTwoInputString = "48";
        int expectedCaseTwoResult = 48;

        int actualCaseTwoResult = stringToInteger.convertStringToInteger(caseTwoInputString);

        assertEquals(expectedCaseTwoResult, actualCaseTwoResult);

        String caseThreeInputString = "-48";
        int expectedCaseThreeResult = -48;

        int actualCaseThreeResult = stringToInteger.convertStringToInteger(caseThreeInputString);

        assertEquals(expectedCaseThreeResult, actualCaseThreeResult);

        String caseFourInputString = "  -66";
        int expectedCaseFourResult = -66;

        int actualCaseFourResult = stringToInteger.convertStringToInteger(caseFourInputString);

        assertEquals(expectedCaseFourResult, actualCaseFourResult);

        String caseFiveInputString = "hello  -66";
        int expectedCaseFiveResult = 0;

        int actualCaseFiveResult = stringToInteger.convertStringToInteger(caseFiveInputString);

        assertEquals(expectedCaseFiveResult, actualCaseFiveResult);

        String caseSixInputString = "222222222222222222";
        int expectedCaseSixResult = Integer.MAX_VALUE;

        int actualCaseSixResult = stringToInteger.convertStringToInteger(caseSixInputString);

        assertEquals(expectedCaseSixResult, actualCaseSixResult);

        String caseSevenInputString = "-222222222222222222";
        int expectedCaseSevenResult = Integer.MIN_VALUE;

        int actualCaseSevenResult = stringToInteger.convertStringToInteger(caseSevenInputString);

        assertEquals(expectedCaseSevenResult, actualCaseSevenResult);
    }

}
