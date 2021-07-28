package com.sj.dac;

public class MaxSubArray02 {

    public static void main(String[] args) {
        int[] a = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaxSubArray02().maxSubArray(a));
    }

    // 分治算法
    public int maxSubArray(int[] nums) {
        return maxSubArray(0, nums.length, nums);
    }

    public int maxSubArray(int begin, int end, int[] nums) {
        if (end - begin < 2)
            return nums[begin];

        int mid = (begin + end) >> 1;

        // 获取中间的最大值
        // [i,mid) + [mid,j)的值
        int leftMax = nums[mid - 1];
        int leftSum = nums[mid - 1];
        for (int i = mid - 2; i >= begin ; i--) {
            leftSum += nums[i];
            if (leftSum > leftMax) {
                leftMax = leftSum;
            }
        }

        int rightMax = nums[mid];
        int rightSum = nums[mid];
        for (int i = mid + 1; i < end ; i++) {
            rightSum += nums[i];
            if (rightSum > rightMax) {
                rightMax = rightSum;
            }
        }

        int midMax= leftMax + rightMax;
        // 求出 [begin,mid)的最大值
        // 求出 [mid,end)的最大值
        // 最大的值为边的最大值
        int side = Math.max(maxSubArray(begin, mid, nums), maxSubArray(mid, end, nums));

        return Math.max(midMax, side);
    }

}
