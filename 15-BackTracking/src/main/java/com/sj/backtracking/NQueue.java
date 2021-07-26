package com.sj.backtracking;

// n皇后问题
public class NQueue {

    private static final int n = 8;
    private static int count = 0;
    private static final int[]cols = new int[n];

    public static void main(String[] args) {
        new NQueue().nQueue();

        System.out.println(count);
    }

    public void nQueue() {
        // 直接在第一行放置一个皇后
        place(0);
    }

    // 存放在某行
    public void place(int row) {

        if (row == n) {
            count++;
            show();
            return;
        }

        // 遍历尝试存放皇后
        for (int i = 0; i < n; i++) {
            // 如果成功则可以存放
            if (isValid(row, i)) {
                // col下标表示
                cols[row] = i;
                place(row + 1);
            }
        }

    }

    private void show() {
        System.out.println("------------------");
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int a = 0;
                if (cols[row] == col) {
                    a = 1;
                }
                System.out.print(a + " ");
            }
            System.out.println("");
        }
        System.out.println("------------------");
    }

    // 查看某一个行 是否能存放在col列中
    boolean isValid(int row, int col) {

        // 查找col数组中 判断是否可以存在
        for (int i = 0; i < row; i++) {

            // cols[i] 表示列的值
            // 表示同一列
            if (col == cols[i]) {
                return false;
            }

            if ((row - i) == Math.abs(col - cols[i])) {
                return false;
            }
            // 求斜率 斜率为1
        }
        return true;
    }

}
