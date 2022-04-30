package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Suite.SuiteClasses({StringRotation.class})
public class StringRotationTest {

    @Test
    public void testRotateString(){

        StringRotation stringRotation = new StringRotation();

        String inputOne = "abcde";
        String goalOne = "bcdea";

        boolean caseOneActualResult = stringRotation.rotateString(inputOne, goalOne);

        assertTrue(caseOneActualResult);

        String inputTwo = "abcde";
        String goalTwo = "acbde";

        boolean caseTwoActualResult = stringRotation.rotateString(inputTwo, goalTwo);

        assertFalse(caseTwoActualResult);

        String inputThree = "abcde";
        String goalThree = "abcdeabcde";

        boolean caseThreeActualResult = stringRotation.rotateString(inputThree, goalThree);

        assertFalse(caseThreeActualResult);



    }
}
