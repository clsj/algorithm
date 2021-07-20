package com.sj.sort;

import java.util.Comparator;

public class BubbleSort01<E> extends Sort<E> {

    public BubbleSort01() {
        this(null);
    }

    public BubbleSort01(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort() {
        for (int i = elements.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (compare(elements[j], elements[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }
}
