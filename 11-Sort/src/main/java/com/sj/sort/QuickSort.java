package com.sj.sort;


import java.util.Comparator;

public class QuickSort<E> extends Sort<E> {

    public QuickSort() {
        this(null);
    }

    public QuickSort(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    protected void sort() {
        sort(0, elements.length - 1);
    }

    private void sort(int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = pivotIndex(begin, end);
        sort(begin, mid -1);
        sort(mid + 1, end);
    }

    private int pivotIndex(int begin, int end) {

        E element = elements[begin];

        boolean right = true;

        while (begin != end) {
            if (right) {
                // end小于begin
                if (compare(element, elements[end]) > 0){
                    elements[begin] = elements[end];
                    right = false;
                }else {
                    end--;
                }
            }else {
                if (compare(element, elements[begin]) < 0){
                    elements[end] = elements[begin];
                    right = true;
                }else {
                    begin++;
                }
            }
        }
        elements[begin] = element;
        return begin;
    }
}
