package com.sj.recursion;

import com.sj.utils.Times;

public class Fibonacci {

    public static void main(String[] args) {

        final int n = 4;

//        Times.test("fib0", new Times.Task() {
//            @Override
//            public void execute() {
//                System.out.println(fib0(n));
//            }
//        });

        Times.test("fib1", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(fib1(n));
            }
        });

        Times.test("fib2", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(fib2(n));
            }
        });

        Times.test("fib3", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(fib3(n));
            }
        });
    }

    public static int fib0(int n) {
        if (n <= 1) {
            return n;
        }

        return fib0(n - 1) + fib0(n - 2);
    }



    public static int fib1(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        return fib1(n, array);
    }

    private static int fib1(int n, int[] array) {
        if (array[n] == 0) {
            // 如果未初始化 初始化数据
            array[n] = fib1(n -1, array) + fib1(n -2, array);
        }
        return array[n];
    }

    public static int fib2(int n) {

        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;

        for (int i = 3; i <= n; i++) {
            array[i] = array[i -1] + array[i -2];
        }
        return array[n];
    }

    public static int fib3(int n) {

        if (n <= 2) {
            return n;
        }

        int left = 1;
        int right = 1;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = left + right;
            left = right;
            right = sum;
        }

        return right;
    }
}
