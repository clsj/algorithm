package com.sj.sort;

import java.text.DecimalFormat;
import java.util.Comparator;

public abstract class Sort<E> {

    protected E[] elements;
    protected Comparator<E> comparator;

    private int cmpCount;
    private int swapCount;
    public long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(E[] elements) {

        if (elements == null || elements.length < 2) {
            return;
        }

        this.elements = elements;

        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    protected void sort() {}

    protected int compare(E e1, E e2) {
        cmpCount++;
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    protected void swap(int index1, int index2) {
        swapCount++;
        E temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        return "【" + getClass().getSimpleName() + "】\n"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";
    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }
}
