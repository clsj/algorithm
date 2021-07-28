package com.sj.dp;

// 01背包
public class Knapsack {

    // dp(i,j) 表示最大最大承重为j，有前i件物品可选 时的最大重量
    // dp(i,0) dp(0,j) 初始值为0
    // 如果 j < weight[i-1] 说明i-1的位置的背包选不了 那么 dp(i,j) = dp(i-1,j)
    // 如果 j >= weight[i-1] 说明i-1的位置的背包可以选
    // 如果不选 dp(i,j) = dp(i-1,j) 如果选择 dp(i,j) = values[i-1] (选择背包的价值) + dp(i-1, j-weight[i-1])  取两者最大值
    public static void main(String[] args) {
        int[] values = new int[] {6, 3, 5, 4, 6};
        int[] weights = new int[] {2, 2, 6, 5, 4};

        int capacity = 10;

//        System.out.println(new Knapsack().ks(values, weights, capacity));
//        System.out.println(new Knapsack().ks2(values, weights, capacity));
//        System.out.println(new Knapsack().ks3(values, weights, capacity));
        System.out.println(new Knapsack().ksEx(values, weights, capacity));
    }

    // 刚好凑够capacity
    public int ksEx(int[] values, int[] weights, int capacity) {
        // 表示最大最大承重为j，有前i件物品可选 时的最大重量
        int[] dp = new int[capacity + 1];

        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i-1]; j--) {
                dp[j] = Math.max(dp[j], values[i-1] + dp[j - weights[i-1]]);
            }
        }

        return dp[capacity] < 0 ? -1 : dp[capacity];
    }

    public int ks4(int[] values, int[] weights, int capacity) {
        // 表示最大最大承重为j，有前i件物品可选 时的最大重量
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i-1]; j--) {
                dp[j] = Math.max(dp[j], values[i-1] +dp[j-weights[i-1]]);
            }
        }
        return dp[capacity];
    }


    public int ks3(int[] values, int[] weights, int capacity) {
        // 表示最大最大承重为j，有前i件物品可选 时的最大重量
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            // 倒着算 可以用一维数组复用
            for (int j = capacity; j >= 1; j--) {
                // 可以优化
                if (j < weights[i-1]) {
                    dp[j] = dp[j];
                }else {
                    dp[j] = Math.max(dp[j], values[i-1] +dp[j-weights[i-1]]);
                }

            }

            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println("");
        }

        return dp[capacity];
    }

    public int ks2(int[] values, int[] weights, int capacity) {
        // 表示最大最大承重为j，有前i件物品可选 时的最大重量
        int[][] dp = new int[2][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            int row = i % 2;
            int prevRow = (i - 1) % 2;
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i-1]) {
                    dp[row][j] = dp[prevRow][j];
                }else {
                    dp[row][j] = Math.max(dp[prevRow][j], values[i-1] +dp[prevRow][j-weights[i-1]]);
                }
                System.out.print(dp[row][j] + " ");
            }
            System.out.println("");
        }

        return dp[values.length % 2][capacity];
    }

    public int ks(int[] values, int[] weights, int capacity) {

        // 表示最大最大承重为j，有前i件物品可选 时的最大重量
        int[][] dp = new int[values.length + 1][capacity + 1];

        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i-1]) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], values[i-1] +dp[i-1][j-weights[i-1]]);
                }
            }
        }

//        for (int i = 0; i < values.length + 1; i++) {
//            for (int j = 0; j < capacity + 1; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println("");
//        }

        return dp[values.length][capacity];
    }

}
