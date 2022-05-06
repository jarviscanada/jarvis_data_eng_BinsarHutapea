package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Two-Sum-ab8b3186e5864ee69b0bc78c9041abe5
 */
public class TwoSum {

    /**
     * O(n) if array is sorted, O(n^2) if not
     * Justification: a for loop is used for checking if the input array is sorted or not
     * @param nums
     * @param target
     */
    public void twoSum(int[] nums, int target){

        boolean isSorted = true;

        for (int i = 1; i < nums.length; i++){
            if (nums[i] < nums[i-1]){
                isSorted = false;
                break;
            }
        }

        if (isSorted == true){
            twoSumTwoPointers(nums, target);
        }
        else{
            twoSumBruteForce(nums, target);
        }

    }

    /**
     * O(n^2)
     * Justification: Nested for-loops
     * @param nums
     * @param target
     * @return array containing matching indices
     */
    public int[] twoSumBruteForce (int[] nums, int target){

        int[] result = new int[2];

        for (int i = 0; i < nums.length-1; i++){
            for (int j = i+1; j < nums.length; j++){
                if (target == ( nums[i]+nums[j] )) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;

    }

    /**
     * O(n)
     * Justification: In the worst case, the two pointers from both ends intersect with each other when iterating
     * through the array
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumTwoPointers (int[] nums, int target){

        int[] result = new int[2];
        int low = 0;
        int high = nums.length-1;

        while (low < high){
            int sum = nums[low] + nums[high];

            if (sum < target){
                low++;
            }
            else if (sum > target){
                high--;
            }
            else{
                result[0] = low;
                result[1] = high;
                break;
            }

        }

        return result;
    }

}
