package com.sj.sort;

import java.util.Comparator;

public class BubbleSort02<E> extends Sort<E> {

    public BubbleSort02() {
        this(null);
    }

    public BubbleSort02(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort() {
        for (int i = elements.length; i > 0; i--) {
            boolean flag = true;
            for (int j = 0; j < i - 1; j++) {
                if (compare(elements[j], elements[j + 1]) > 0) {
                    swap(j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
