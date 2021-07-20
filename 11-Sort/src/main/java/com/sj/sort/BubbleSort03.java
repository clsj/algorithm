package com.sj.sort;

import java.util.Comparator;

public class BubbleSort03<E> extends Sort<E> {

    public BubbleSort03() {
        this(null);
    }

    public BubbleSort03(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort() {
        for (int i = elements.length; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 0; j < i - 1; j++) {
                if (compare(elements[j], elements[j + 1]) > 0) {
                    swap(j, j + 1);
                    maxIndex = j + 1;
                }
            }
            i = maxIndex + 1;
        }
    }

}
