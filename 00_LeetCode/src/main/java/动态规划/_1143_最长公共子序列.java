package 动态规划;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class _1143_最长公共子序列 {

    public int longestCommonSubsequence(String text1, String text2) {

        String rowStr = text1;
        String colStr = text2;

        if (text1.length() < text2.length()) {
            rowStr = text2;
            colStr = text1;
        }

        int[] dp = new int[colStr.length() + 1];
        for (int i = 1; i <= rowStr.length(); i++) {
            int current = 0;
            for (int j = 1; j <= colStr.length(); j++) {
                int leftTop = current;
                current = dp[j];
                if (rowStr.charAt(i -1) == colStr.charAt(j-1)) {
                    dp[j] = leftTop + 1;
                }else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
            }
        }
        return dp[colStr.length()];
    }

}
