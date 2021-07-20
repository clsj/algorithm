package com.sj.sort;

import java.util.Comparator;

/**
 * 堆排序
 * @param <E>
 */
public class HeapSort<E> extends Sort<E> {

    public HeapSort() {
        this(null);
    }

    public HeapSort(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int heapSize;

    @Override
    protected void sort() {

        heapSize = elements.length;

        // 先建堆
        for (int i = (heapSize >> 1) - 1; i >= 0 ; i--) {
            siftDown(i);
        }

        while (heapSize > 1) {
            // 交换头和尾的数据
            swap(0, --heapSize);
            // 堆下滤
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        E element = elements[index];

        int left = (index << 1) + 1;
        int right = (index << 1) + 2;

        int maxElementIndex = getMaxElementIndex(left, right);
        if (maxElementIndex == -1) {
            return;
        }

        if (compare(element, elements[maxElementIndex]) >= 0) {
            return;
        }

        // 交换
        elements[index] = elements[maxElementIndex];
        elements[maxElementIndex] = element;
        siftDown(maxElementIndex);

    }

    private int getMaxElementIndex(int left, int right) {
        if (left >= heapSize) {
            return -1;
        }

        if (right >= heapSize) {
            return left;
        }

        return compare(elements[left], elements[right]) > 0 ? left : right;
    }
}
