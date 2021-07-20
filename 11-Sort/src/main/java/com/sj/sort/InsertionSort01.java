package com.sj.sort;

import java.util.Comparator;

public class InsertionSort01<E> extends Sort<E> {

    public InsertionSort01() {
        this(null);
    }

    public InsertionSort01(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort() {
        // 从1开始到最后
        for (int start = 1; start < elements.length; start++) {
            int end = start;
            // 从start开始和前边比较 交换
            while (end > 0 && compare(elements[end], elements[end -1]) < 0) {
                swap(end, end - 1);
                end--;
            }
        }
    }
}
