package com.sj.sort;

import java.util.Comparator;

public class SelectionSort<E> extends Sort<E> {

    public SelectionSort() {
        this(null);
    }

    public SelectionSort(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    protected void sort() {
        for (int i = elements.length; i > 0; i--) {

            int maxIndex = 0;

            for (int j = 1; j < i; j++) {
                if (compare(elements[maxIndex], elements[j]) < 0) {
                    maxIndex = j;
                }
            }
            // 得到最大的index
            swap(maxIndex, i - 1);
        }
    }
}
