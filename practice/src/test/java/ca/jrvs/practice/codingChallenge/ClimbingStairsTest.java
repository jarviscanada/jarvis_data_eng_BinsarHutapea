package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({ClimbingStairs.class})
public class ClimbingStairsTest {

    @Test
    public void testClimbingStairs(){

        ClimbingStairs climbingStairs = new ClimbingStairs();

        int caseOneNum = climbingStairs.getNumOfWaysForClimbingWithRecursion(3);
        int expectedCaseOneResult = 3;

        assertEquals(expectedCaseOneResult, caseOneNum);

        int caseTwoNum = climbingStairs.getNumOfWaysForClimbingWithRecursion(5);
        int expectedCaseTwoResult = 8;

        assertEquals(expectedCaseTwoResult, caseTwoNum);

        int caseOneDPNum = climbingStairs.getNumOfWaysForClimbingWithDP(3);

        assertEquals(expectedCaseOneResult, caseOneDPNum);

        int caseTwoDPNum = climbingStairs.getNumOfWaysForClimbingWithDP(5);

        assertEquals(expectedCaseTwoResult, caseTwoDPNum);


    }
}
