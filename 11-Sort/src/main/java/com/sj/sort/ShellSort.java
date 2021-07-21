package com.sj.sort;

import java.util.Comparator;

public class ShellSort<E> extends Sort<E> {

    public ShellSort() {
        this(null);
    }

    public ShellSort(Comparator<E> comparator) {
        this.comparator = comparator;
    }
}
