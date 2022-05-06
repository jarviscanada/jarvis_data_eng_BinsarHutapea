package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({LetterWithNumber.class})
public class LetterWithNumberTest {

    @Test
    public void testPrintLetterWIthNumber(){

        LetterWithNumber letterWithNumber = new LetterWithNumber();

        String caseOneString = "a";
        String expectedCaseOneOutput = "a1";

        String actualCaseOneOutput = letterWithNumber.printLetterWithNumber(caseOneString);

        assertArrayEquals(expectedCaseOneOutput.toCharArray(), actualCaseOneOutput.toCharArray());

        String caseTwoString = "aej";
        String expectedCaseTwoOutput = "a1e5j10";

        String actualCaseTwoOutput = letterWithNumber.printLetterWithNumber(caseTwoString);

        assertArrayEquals(expectedCaseTwoOutput.toCharArray(), actualCaseTwoOutput.toCharArray());

    }
}
