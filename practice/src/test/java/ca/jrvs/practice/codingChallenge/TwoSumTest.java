package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.assertArrayEquals;

@Suite.SuiteClasses({TwoSum.class})
public class TwoSumTest {

    @Test
    public void testTwoSumBruteForce(){

        TwoSum twoSum = new TwoSum();

        int[] nums = {3,2,4};
        int target = 6;
        int[] expectedResult = {1, 2};

        int[] result = twoSum.twoSumBruteForce(nums, target);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testTwoSumTwoPointers(){

        TwoSum twoSum = new TwoSum();

        int[] nums = {2,7,11,15};
        int target = 9;
        int[] expectedResult = {0, 1};

        int[] result = twoSum.twoSumTwoPointers(nums, target);

        assertArrayEquals(expectedResult, result);
    }


}

