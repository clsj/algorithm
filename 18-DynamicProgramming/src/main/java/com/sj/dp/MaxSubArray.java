package com.sj.dp;

public class MaxSubArray {

    public static void main(String[] args) {
        int[] a = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaxSubArray().maxSubArray(a));
    }

    public int maxSubArray(int[] nums) {
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp > 0) {
                dp = nums[i] +dp;
            }else {
                dp = nums[i];
            }
            if (dp > max) {
                max = dp;
            }
        }
        return max;
    }

    //
    public int maxSubArray2(int[] nums) {

        // dp[i] 表示以i结尾，最大的连续子序列的和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int rightIndex = 0;
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = nums[i] +dp[i -1];
            }else {
                dp[i] = nums[i];
            }

            if (dp[i] > max) {
                max = dp[i];
                rightIndex = i;
            }
        }

        int leftIndex = rightIndex;
        for (int i = rightIndex; i >= 0; i--) {
            if (dp[i] > 0) {
                leftIndex = i;
            }else {
                break;
            }
        }
        System.out.println("left -> " + leftIndex + " right -> " + rightIndex);

        for (int i = leftIndex; i <= rightIndex ; i++) {
            System.out.print(nums[i] + " -> ");
        }
        System.out.println("");
        return dp[rightIndex];
    }



}
