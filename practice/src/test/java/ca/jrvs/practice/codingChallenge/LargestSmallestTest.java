package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({LargestSmallest.class})
public class LargestSmallestTest {

    @Test
    public void testFindLargestNum(){

        LargestSmallest largestSmallest = new LargestSmallest();

        int[] num = { 3, 4, 9, 18, 2, 4, 1, 4 };

        int expectedResult = 18;

        int actualResult = largestSmallest.findLargestNum(num);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testFindSmallestNum(){

        LargestSmallest largestSmallest = new LargestSmallest();

        int[] num = { 3, 4, 9, 18, 2, 4, 1, 4 };

        int expectedResult = 1;

        int actualResult = largestSmallest.findSmallestNum(num);

        assertEquals(expectedResult, actualResult);

    }

}
