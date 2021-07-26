package com.sj.recursion;

// 汉诺塔
public class Hanoi {

    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        hanoi.hanoi(4, "A", "B", "C");
    }

    public void hanoi(int n, String p1, String p2, String p3 ) {
        // 当n = 1 直接从p1移动到p2
        if (n == 1) {
            move(1, p1, p3);
            return;
        }

        // 将n-1个盘子从p1移动到p2
        hanoi(n-1, p1, p3, p2);
        // 将n个盘子移动到p3
        move(n, p1, p3);
        // 将n-1个盘子从p2移动到p3
        hanoi(n-1, p2, p1, p3);
    }

    // 移动
    private void move(int n, String from, String to) {
        System.out.println("第" + n + "号盘子 从" + from + "移动到" + to);
    }

}
