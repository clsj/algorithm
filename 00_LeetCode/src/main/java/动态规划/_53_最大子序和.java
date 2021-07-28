package 动态规划;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class _53_最大子序和 {
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
}
