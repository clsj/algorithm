package 动态规划;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class _300_最长递增子序列 {

    public int lengthOfLIS(int[] nums) {

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

//    public int lengthOfLIS(int[] nums) {
//        int[] dp = new int[nums.length];
//        dp[0] = 1;
//        int maxDp = dp[0];
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = 1;
//            for (int j = i; j >= 0; j--) {
//                if (nums[i] > nums[j]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//            if (dp[i] > maxDp) {
//                maxDp = dp[i];
//            }
//        }
//        return maxDp;
//    }
}
