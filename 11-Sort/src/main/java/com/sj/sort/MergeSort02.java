package com.sj.sort;

import java.util.Comparator;

public class MergeSort02<E> extends Sort<E> {

    public MergeSort02() {
        this(null);
    }

    public MergeSort02(Comparator<E> comparator) {
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

    private void merge(int begin, int mid, int end) {

        // leftCount
        int leftCount = mid - begin;
        E[] leftElement = (E[]) new Object[leftCount];
        int lcount = 0;
        for (int i = begin; i < mid; i++) {
            leftElement[lcount++] = elements[i];
        }

        int count = begin;
        int leftIndex = 0;
        int rightIndex = mid;

        while (leftIndex < leftCount && rightIndex < end) {
            if (compare(leftElement[leftIndex], elements[rightIndex]) > 0) {
                elements[count++]  = elements[rightIndex++];
            }else {
                elements[count++] = leftElement[leftIndex++];
            }
        }

        while (leftIndex < leftCount) {
            elements[count++] = leftElement[leftIndex++];
        }

        while (rightIndex < end) {
            elements[count++]  = elements[rightIndex++];
        }
    }
}
