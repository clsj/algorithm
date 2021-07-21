package com.sj.sort;

import java.util.Comparator;

public class InsertionSort03<E> extends Sort<E> {

    public InsertionSort03() {
        this(null);
    }

    public InsertionSort03(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort() {
        // 从1开始到最后
        for (int start = 1; start < elements.length; start++) {

            E startElement = elements[start];

            // 找到index
            int index = search(startElement, start);

            for (int end = start; end > index; end--) {
                elements[end] = elements[end - 1];
            }

            elements[index] = startElement;

        }
    }

    // 找到第一个大于value的位置
    private int search(E value, int end) {

        int begin = 0;

        while (end > begin) {
            int mid = (end + begin) >> 1;
            if (compare(value, elements[mid]) >= 0) {
                begin = mid + 1;
            }else {
                end = mid;
            }
        }
        return begin;
    }

}
