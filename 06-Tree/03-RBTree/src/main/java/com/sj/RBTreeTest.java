package com.sj;

import com.sj.printer.BinaryTrees;

public class RBTreeTest {
    public static void main(String[] args) {
//        int[] array = new int[] {52};
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        RBTree<Integer> rbTree = new RBTree<>();

        for (int value : array) {
            rbTree.add(value);

        }
        BinaryTrees.print(rbTree);
        System.out.println("");
        System.out.println("========");
        for (int value : array) {

            System.out.println("【" + value +"】");

            rbTree.remove(value);

            BinaryTrees.print(rbTree);
            System.out.println("");
            System.out.println("========");
        }

    }
}
