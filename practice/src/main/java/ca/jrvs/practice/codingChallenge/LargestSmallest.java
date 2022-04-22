package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Find-Largest-Smallest-9d0d94b714e24815b4814f5cadb3c89f
 */
public class LargestSmallest {

    /**
     * O(n)
     * Justification: A for loop is used to iterate through the input array
     * @param nums input array
     * @return largest number in the array
     */
    public int findLargestNum(int[] nums){

        int result = Integer.MIN_VALUE;

        for (int num : nums){

            result = Math.max(num, result);

        }

        return result;
    }

    /**
     * O(n)
     * Justification: A for loop is used to iterate through the input array
     * @param nums input array
     * @return smallest number in the array
     */
    public int findSmallestNum(int[] nums){

        int result = Integer.MAX_VALUE;

        for (int num : nums){

            result = Math.min(num, result);

        }

        return result;

    }

}
