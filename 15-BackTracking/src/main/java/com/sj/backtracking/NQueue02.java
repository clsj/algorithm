package com.sj.backtracking;

// n皇后问题
public class NQueue02 {

    private static int count = 0;

    // 表示第几列是否有值
    private static boolean[] cols;

//    7  8  9  10  11  12  13  14
//    6  7  8  9  10  11  12  13
//    5  6  7  8  9  10  11  12
//    4  5  6  7  8  9  10  11
//    3  4  5  6  7  8  9  10
//    2  3  4  5  6  7  8  9
//    1  2  3  4  5  6  7  8
//    0  1  2  3  4  5  6  7
    // 表示左上斜线的下标是否有值
    private static boolean[] leftSlants;

//    0  1  2  3  4  5  6  7
//    1  2  3  4  5  6  7  8
//    2  3  4  5  6  7  8  9
//    3  4  5  6  7  8  9  10
//    4  5  6  7  8  9  10  11
//    5  6  7  8  9  10  11  12
//    6  7  8  9  10  11  12  13
//    7  8  9  10  11  12  13  14
    // 表示右上斜线的下标是否有值
    private static boolean[] rightSlants;

    private static int[]colsData;

    public static void main(String[] args) {
        NQueue02 queue02 = new NQueue02();
        final int n = 8;
        queue02.nQueue(n);
        System.out.println(count);
    }

    public void nQueue(int n) {
        colsData = new int[n];
        cols = new boolean[n];
        leftSlants = new boolean[(n << 1) - 1];
        rightSlants = new boolean[(n << 1) - 1];
        // 直接在第一行放置一个皇后
        place(0);
    }

    // 存放在某行
    public void place(int row) {

        if (row == cols.length) {
            count++;
            show();
            return;
        }

        // 遍历尝试存放皇后
        for (int i = 0; i < cols.length; i++) {

            // 如果成功则可以存放
            if (isValid(row, i)) {

                // col下标表示
                cols[i] = true;
                leftSlants[getLeftSlant(row, i)] = true;
                rightSlants[getRightSlant(row, i)] = true;
                colsData[row] = i;
                place(row + 1);
                // 走到下边说明是回溯
                // 当回溯的时候要设为false
                cols[i] = false;
                leftSlants[getLeftSlant(row, i)] = false;
                rightSlants[getRightSlant(row, i)] = false;
            }
        }
    }

    private void show() {
        System.out.println("------------------");
        for (int colsDatum : colsData) {
            for (int col = 0; col < colsData.length; col++) {
                int a = 0;
                if (colsDatum == col) {
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
        // cols[i] 表示列的值
        // 表示同一列
        if (cols[col]) {
            return false;
        }

        if (leftSlants[getLeftSlant(row, col)]) {
            return false;
        }

        if (rightSlants[getRightSlant(row, col)]) {
            return false;
        }

        return true;
    }

    private int getLeftSlant(int row, int col) {
        return col - row + cols.length - 1;
    }

    private int getRightSlant(int row, int col) {
        return col + row;
    }
}
