package com.sj.sort;

import java.util.Comparator;

public class MergeSort01<E> extends Sort<E> {

    public MergeSort01() {
        this(null);
    }

    public MergeSort01(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    protected void sort() {
        sort(0, elements.length);
    }

    private void sort(int begin, int end) {
        if ((end - begin) < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    // 合并
    private void merge(int begin, int mid, int end) {

        E[] cpoy = (E[])new Object[end - begin];

        int count = 0;
        int leftIndex = begin;
        int rightIndex = mid;

        while (leftIndex < mid && rightIndex < end) {
            if (compare(elements[leftIndex], elements[rightIndex]) > 0) {
                cpoy[count++]  = elements[rightIndex++];
            }else {
                cpoy[count++] = elements[leftIndex++];
            }
        }

        while (leftIndex < mid) {
            cpoy[count++] = elements[leftIndex++];
        }

        while (rightIndex < end) {
            cpoy[count++]  = elements[rightIndex++];
        }

        for (E e : cpoy) {
            elements[begin++] = e;
        }
    }
}
