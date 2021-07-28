package com.sj.dp;

// 最长公共子序列
public class LCS {
    //        dp[i,j] 表示text1中前i个元素和text2中前j个元素的最长子序列的长度
//        dp[0,j] dp[i,0] = 0
//        text1[i -1] == text2[j -1] 那个dp[i,j] = dp[i-1, j-1] + 1
//        text1[i -1] != text2[j -1] 那个dp[i,j] = max(dp[i-1, j],dp[i,j-1])
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
//        String text1 = "ezupkr";
//        String text2 = "ubmrapg";

//        String text1 = "pmjghexybyrgzczy";
//        String text2 = "hafcdqbgncrcbihkd";
        System.out.println(new LCS().longestCommonSubsequence4(text1, text2));

    }

    public int longestCommonSubsequence4(String text1, String text2) {

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

    // 优化 使用两行数组
    public int longestCommonSubsequence3(String text1, String text2) {

        int[] dp = new int[text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            int current = 0;
            for (int j = 1; j <= text2.length(); j++) {
                int leftTop = current;
                current = dp[j];
                if (text1.charAt(i -1) == text2.charAt(j-1)) {
                    dp[j] = leftTop + 1;
                }else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
            }
        }
        return dp[text2.length()];
    }

    // 优化 使用两行数组
    public int longestCommonSubsequence2(String text1, String text2) {

        int[][] dp = new int[2][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                int row = i % 2;
                int prevRow = (i - 1) % 2;
                if (text1.charAt(i -1) == text2.charAt(j-1)) {
                    dp[row][j] = dp[prevRow][j - 1] + 1;
                }else {
                    dp[row][j] = Math.max(dp[prevRow][j], dp[row][j-1]);
                }
            }
        }
        return dp[text1.length() % 2][text2.length()];
    }

    public int longestCommonSubsequence(String text1, String text2) {

        int m = text1.length() + 1;
        int n = text2.length() + 1;

        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i -1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m -1][n -1];
    }
}
