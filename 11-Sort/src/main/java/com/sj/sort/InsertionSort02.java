package com.sj.sort;

import java.util.Comparator;

public class InsertionSort02<E> extends Sort<E> {

    public InsertionSort02() {
        this(null);
    }

    public InsertionSort02(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort() {
        // 从1开始到最后
        for (int start = 1; start < elements.length; start++) {
            int end = start;

            E startElement = elements[start];
            // 从start开始和前边比较 交换
            while (end > 0 && compare(startElement, elements[end -1]) < 0) {
                elements[end] = elements[end - 1];
                end--;
            }
            elements[end] = startElement;
        }
    }

}
