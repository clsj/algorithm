package com.sj;

import com.sj.printer.BinaryTrees;

import java.util.Comparator;

public class HeapTest {

    public static void test01() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        heap.add(20);
        heap.add(15);
        heap.add(30);
        heap.add(11);
        heap.add(35);
        heap.add(45);
        heap.add(40);
        BinaryTrees.println(heap);
        System.out.println(" ");
        System.out.println("--------------------");
//        while (heap.size > 0) {
//            heap.remove();
//            BinaryTrees.println(heap);
//            System.out.println(" ");
//            System.out.println("--------------------");
//        }


        heap.replace(1);
        BinaryTrees.println(heap);
        System.out.println(" ");
        System.out.println("--------------------");
        heap.remove();
//        BinaryTrees.println(heap);
//        System.out.println(" ");
//        System.out.println("--------------------");
    }

    public static void demo02() {
        Integer[] data = new Integer[]{73, 45, 49, 87, 34, 29, 44, 77, 79, 41, 31, 19, 100, 14, 63, 82};

        // 根据比较就可以修改
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });

        BinaryTrees.println(binaryHeap);
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{73, 45, 49, 87, 34, 29, 44, 77, 79, 41, 31, 19, 100, 14, 63, 82, 17, 91, 51, 92, 18, 90, 34, 74, 59, 50, 10, 27, 77, 25, 46, 95, 78, 52, 36, 83, 30, 3, 67, 38, 86, 24, 29, 81, 23, 16, 5, 71, 14, 65, 32, 79, 66, 33, 28, 56, 96, 55, 6, 63, 70, 2, 57, 98, 22, 84};
        // topK
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        };

        // 小顶堆
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(comparator);

        int top = 5;

        for (Integer datum : data) {

            if (binaryHeap.size < top) {
                binaryHeap.add(datum);
            } else if (comparator.compare(datum, binaryHeap.get()) < 0) {
                    binaryHeap.replace(datum);
            }

        }
        BinaryTrees.println(binaryHeap);
    }

}
