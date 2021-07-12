package com.sj;

import com.sj.util.TimeUtil;

public class Main {

    public static void main(String[] args) {

        final int n = 45;

        TimeUtil.time("fib1", new TimeUtil.Task() {
            public void excute() {
                System.out.println(fib1(n));
            }
        });

        TimeUtil.time("fib2", new TimeUtil.Task() {
            public void excute() {
                System.out.println(fib2(n));
            }
        });
    }

    public static int fib1(int n) {
        if (n <= 1) {
            return n;
        }

        return fib1(n - 1) + fib1(n - 2);
    }

    public static int fib2(int n) {

        if (n <= 1) {
            return n;
        }

        int left = 0;
        int right = 1;

        for (int i = 0; i < n -1; i++) {
            int sum = left + right;
            left = right;
            right = sum;
        }

        return right;
    }
}
