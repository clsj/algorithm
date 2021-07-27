package com.sj;

import com.sj.printer.BinaryTrees;

public class AVLTest {

    public static void main(String[] args) {
        int[] array = new int[] {16, 33, 8, 53, 14, 37, 48, 28, 72, 78, 32, 98, 120, 130, 140, 150, 160, 170, 180};
//        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        AVLTree<Integer> avl = new AVLTree<>();

        for (int value : array) {
            avl.add(value);
        }
        BinaryTrees.print(avl);
        System.out.println("");
        System.out.println("--------------");
    }

}
