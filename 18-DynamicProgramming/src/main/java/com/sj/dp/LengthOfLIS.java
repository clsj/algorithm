package com.sj.dp;

import java.util.Arrays;

// dp[i] 表示以nums[i]结尾的最长上升子序列
public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = new int[] {10,9,2,5,3,7,101,18};
//        int[] nums = new int[] {0,1,0,3,2,3};
//        int[] nums = new int[] {7,7,7,7,7,7,7};
//        int[] nums = new int[] {10,2,2,5,1,7,101,18};

//        dp(0) = 1
//        dp(1) = 1
//        dp(2) = 1
//        dp(3) = dp(2) + 1 = dp(1) + 1 = 2
//        dp(4) = 1
//        dp(5) = dp(3) + 1 = 3
//        dp(6) = dp(5) + 1 = 4
//        dp(7) = dp(5) + 1 = 4

        System.out.println(new LengthOfLIS().lengthOfLIS3(nums));
        System.out.println(new LengthOfLIS().lengthOfLIS(nums));
        System.out.println(new LengthOfLIS().lengthOfLIS4(nums));

    }

    public int lengthOfLIS4(int[] nums) {

        int[] top = new int[nums.length];
        Arrays.fill(top, Integer.MAX_VALUE);
        int len = 0;
        for (int num : nums) {
            int begin = 0;
            int end = len + 1;
            while (end > begin) {
                int mid = (end + begin) >> 1;
                if (num > top[mid]) {
                    begin = mid + 1;
                }else {
                    end = mid;
                }
            }
            top[begin] = num;
            if (begin == len) {
                len++;
            }
        }
        return len;
    }

    // 找到第一个大于value的位置
    private int search(int[] elements, int value, int end) {
        int begin = 0;
        while (end > begin) {
            int mid = (end + begin) >> 1;
            if (value > elements[mid]) {
                begin = mid + 1;
            }else {
                end = mid;
            }
        }
        return begin;
    }

    public int lengthOfLIS3(int[] nums) {

        int[] top = new int[nums.length];
        Arrays.fill(top, Integer.MAX_VALUE);
        int len = 0;
        for (int num : nums) {
            int j = 0;
            while (j < len) {
                if (num <= top[j]) {
                    top[j] = num;
                    break;
                }
                j++;
            }
            if (j == len) {
                len++;
                top[j] = num;
            }
        }
        return len;
    }


    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxDp = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxDp) {
                maxDp = dp[i];
            }
        }
        return maxDp;
    }

    public int lengthOfLIS2(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxDp = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i; j >= 0; j--) {
//                nums[i] 和前边做比较
                if (nums[i] > nums[j]) {
                    // 获取j的dp[j]
                    // 循环完毕 找到最大的dp
                    if (dp[i] < (dp[j] + 1)) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            if (dp[i] > maxDp) {
                maxDp = dp[i];
            }
        }


        for (int i = 0; i < dp.length; i++) {
            System.out.println(dp[i]);
        }

        return maxDp;
    }

}
