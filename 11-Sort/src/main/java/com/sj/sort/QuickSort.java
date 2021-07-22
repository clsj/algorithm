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
        sort(0, elements.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = pivotIndex(begin, end);
        sort(begin, mid);
        sort(mid + 1, end);
    }

    private int pivotIndex(int begin, int end) {

        E element = elements[begin];

        end--;

        while (begin < end) {
            while (begin < end) {
                if (compare(element, elements[end]) < 0){
                    end--;
                }else {
                    // 当赋值过后begin一定要加1 否则 两者相等的时候会出现死循环
                    elements[begin++] = elements[end];
                    break;
                }
            }

            while (begin < end) {
                if (compare(element, elements[begin]) > 0){
                    begin++;
                }else {
                    elements[end--] = elements[begin];
                    break;
                }
            }
        }
        elements[begin] = element;
        return begin;
    }
}
