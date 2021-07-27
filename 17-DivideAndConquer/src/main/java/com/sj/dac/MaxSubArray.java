package com.sj.dac;

public class MaxSubArray {

    public static void main(String[] args) {
        int[] a = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaxSubArray().maxSubArray(a));
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 求出和
                int sum = sum(i, j, nums);
                max = Math.max(max, sum);
            }
        }

        return max;
    }

    private int sum(int from, int to, int[] nums) {

        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += nums[i];
        }
        return sum;
    }

}
