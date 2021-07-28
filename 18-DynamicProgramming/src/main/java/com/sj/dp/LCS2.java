package com.sj.dp;

// 最长公共子串
public class LCS2 {
    // dp[i,j] 表示以text1(i-1) text2(j-1) 结尾的最长公共子串
    // text1(i-1) = text2(j-1) 那个dp[i,j] = dp[i-1, j -1] + 1
    // text1(i-1) != text2(j-1) dp[i,j] = 0
    public static void main(String[] args) {
        String text1 = "abcdea";
        String text2 = "baebcde";

        System.out.println(new LCS2().longestSubstring(text1, text2));
        System.out.println(new LCS2().longestSubstring2(text1, text2));
    }

    public int longestSubstring2(String text1, String text2){

        String rowStr = text1;
        String colStr = text2;
        int max = 0;
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
                }
                if (dp[j] > max) {
                    max = dp[j];
                }
            }
        }
        return max;
    }


    // 最长公共子串
    public int longestSubstring(String text1, String text2){

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        int max = 0;
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i -1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }

                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

}
