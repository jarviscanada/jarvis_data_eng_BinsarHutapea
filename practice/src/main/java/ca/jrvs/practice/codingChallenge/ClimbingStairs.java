package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-051d0950ec344547accc42a13610b398
 */
public class ClimbingStairs {

    /**
     * O(2^n)
     * Justification:
     * T(n) = O(1) for n <= 3, else it is T(n-1) + T(n-2) + O(1), O(1) is the time complexity of if block operation
     * An illustration with a recursion tree shows that the height is n-1.
     * At each level, the total of work done is 2^(i-1), where i ranges from 0 to n-1 (2^0 + 2^1 +....2^(n-1))
     * By geometric series, the sum of the work is 2^n.
     * @param n
     * @return
     */
    public int getNumOfWaysForClimbingWithRecursion(int n){

        if (n <= 3)
            return n;

        return getNumOfWaysForClimbingWithRecursion(n-2)+getNumOfWaysForClimbingWithRecursion(n-1);
    }

    /**
     * O(n)
     * Justification: A for loop that iterates through an array that has the length of n-1 ~ n
     * @param n
     * @return
     */
    public int getNumOfWaysForClimbingWithDP(int n){

        if (n <= 3)
            return n;

        int[] ways = new int[n + 1];
        ways[0] = 0;
        ways[1] = 1;
        ways[2] = 2;
        ways[3] = 3;

        for (int i = 4; i < ways.length; i++)
            ways[i] = ways[i-2] + ways[i-1];

        return ways[n];
    }
}
