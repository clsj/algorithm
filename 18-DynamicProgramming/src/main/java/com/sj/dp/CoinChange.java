package com.sj.dp;

// 定义dp[n]表示money的钱使用的最少银币数
// dp[n] = dp[n-25] + 1
// dp[n] = dp[n-20] + 1
// dp[n] = dp[n-5] + 1
// dp[n] = dp[n-1] + 1
public class CoinChange {

    public static void main(String[] args) {

        new CoinChange().coins5(41, new int[]{1, 5, 10, 20, 25});
//        System.out.println(new CoinChange().coins5(19, new int[]{1, 5, 10, 20, 25}));
    }

    // 通用的
    int coins5(int money, int[] faces) {

        if (money < faces[0]) {
            return -1;
        }

        int[] dp = new int[money + 1];
        int[] faceList = new int[money + 1];
        for (int i = faces[0]; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;

            for (int face : faces) {
                if (face <= i && dp[i - face] < min) {
                    min = dp[i - face];
                    faceList[i] = face;
                }
            }

            dp[i] = min + 1;
            print(faceList, i);
        }

        return dp[money];
    }


    // 递推
    int coins4(int money) {
        int[] dp = new int[money + 1];
        // 表示凑够i分钱，最后用到的面值
        int[] face = new int[money + 1];
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;

            if (dp[i -1] < min) {
                min = dp[i - 1];
                face[i] = 1;
            }

            if (i >= 5 && dp[i -5] < min) {
                min = dp[i - 5];
                face[i] = 5;
            }

            if (i >= 20 && dp[i -20] < min) {
                min = dp[i - 20];
                face[i] = 20;
            }

            if (i >= 25 && dp[i -25] < min) {
                min = dp[i - 25];
                face[i] = 25;
            }

            dp[i] = min + 1;

            print(face, i);
        }

//        print(face, money);

        return dp[money];
    }

    private void print(int[] face, int money) {
        System.out.print("[" + money +"]: ");
        int n = money;
        int count = 0;
        while (n > 0) {
            System.out.print(face[n] + " ");
            count++;
            n = n - face[n];
        }
        System.out.print(" 需要" + count + "枚硬币");
        System.out.println("");
    }

    // 递推
    int coins3(int money) {
        int[] dp = new int[money + 1];

        for (int i = 1; i < dp.length; i++) {
            int min = dp[i - 1];
            // 不断的求出dp
            if (i >= 5) {
                min = Math.min(dp[i - 5], min);
            }

            if (i >= 20) {
                min = Math.min(dp[i - 20], min);
            }

            if (i >= 25) {
                min = Math.min(dp[i - 25], min);
            }
            dp[i] = min + 1;
        }
        return dp[money];
    }

    // 记忆化搜索
    int coins2(int money) {
        // 如果计算的钱数小于0 返回int的最大值 保证取到正确的数据
        // 存储一个数组，如果数据已经有的就直接取出
        int[] dp = new int[money + 1];
        int[] m = new int[] {1, 5, 20, 25};
        for (int j : m) {
            if (j < money) {
                dp[j] = 1;
            }
        }
        return coins2(money, dp);
    }

    int coins2(int money, int[] dp) {

        if (money < 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[money] == 0) {
            int min1 = Math.min(coins2(money - 25, dp), coins2(money - 20, dp));
            int min2 = Math.min(coins2(money - 5, dp), coins2(money - 1, dp));
            dp[money] = Math.min(min1, min2) + 1;
        }

        return dp[money];
    }
    // 暴力递归
    int coins1(int money) {
        // 如果计算的钱数小于0 返回int的最大值 保证取到正确的数据
        if (money < 0) {
            return Integer.MAX_VALUE;
        }

        if (money == 25 || money == 20 || money == 5 || money == 1) {
            return 1;
        }
        int min1 = Math.min(coins1(money - 25), coins1(money - 20));
        int min2 = Math.min(coins1(money - 5), coins1(money - 1));
        return Math.min(min1, min2) + 1;
    }

}
