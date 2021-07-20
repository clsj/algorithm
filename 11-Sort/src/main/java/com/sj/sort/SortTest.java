package com.sj.sort;

import com.sj.utils.Asserts;
import com.sj.utils.Integers;

import java.util.Arrays;
import java.util.Comparator;


public class SortTest {

    public static void sort(Integer[] data, Sort... sorts) {

        for (Sort sort : sorts) {
            Integer[] copy = Integers.copy(data);
            sort.sort(copy);
            Asserts.test(Integers.isAscOrder(copy));
        }

        Arrays.sort(sorts, new Comparator<Sort>() {
            @Override
            public int compare(Sort o1, Sort o2) {
                return (int) (o1.time - o2.time);
            }
        });

        for (Sort sort : sorts) {
            System.out.println(sort);
        }

    }

    public static void main(String[] args) {
        Integer[] data = Integers.random(5000, 1, 1000000);
//        Integer[] data = Integers.ascOrder(1, 10000);

//        sort(data, new BubbleSort03<Integer>(), new SelectionSort<Integer>(), new HeapSort<Integer>());
        sort(data, new InsertionSort02<>(), new BubbleSort03<Integer>());
    }


}
