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
        Integer[] data = Integers.random(500000, 1, 500);
//        Integer[] data = Integers.random(4, 1, 20);
//        Integer[] data = Integers.ascOrder(1, 10000);
//        Integer[] data = new Integer[] {10, 10, 2, 23, 40};
        sort(data, new MergeSort01<>(),new MergeSort02<>(), new HeapSort<>(), new QuickSort<>());

//        sort(data, new QuickSort<>());
    }


}
