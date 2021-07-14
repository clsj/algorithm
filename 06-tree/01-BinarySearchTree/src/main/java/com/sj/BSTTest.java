package com.sj;

import com.sj.printer.BinaryTrees;

/**
 * @author li
 * @since 2021/7/14
 */
public class BSTTest {

    public static void main(String[] args) {
        int[] array = new int[] {20, 10, 30, 12, 6, 32, 26, 3, 18, 11, 9};

        BST<Integer> bst = new BST<>();

        for (int value : array) {
            bst.add(value);
        }
        BinaryTrees.print(bst);
        System.out.println("");
        System.out.println(bst.size);
    }

    public static void demo01() {
        int[] array = new int[] {20, 10, 30, 12, 6};

        BST<Integer> BST = new BST<>();

        for (int value : array) {
            BST.add(value);
        }
        BinaryTrees.print(BST);
        System.out.println("");
        System.out.print("前序遍历: ");
        BST.preorderTraversal(new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + "->");

                if (element == 12) return true;

                return false;
            }
        });
        System.out.println("");
        System.out.print("中序遍历: ");
        BST.inorderTraversal(new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + "->");
                if (element == 6) return true;
                return false;
            }
        });

        System.out.println("");
        System.out.print("后序遍历: ");
        BST.postorderTraversal(new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + "->");
                if (element == 30) return true;
                return false;
            }
        });
        System.out.println("");
        System.out.print("层序遍历: ");
        BST.levelOrderTraversal(new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + "->");
                if (element == 10) return true;
                return false;
            }
        });
    }

    public static void demo02() {
        int[] array = new int[] {20, 10, 30, 12, 6, 32, 26, 3, 18, 11, 9};
//        int[] array = new int[] {20, 10, 30};
//        int[] array = new int[] {20, 10, 30};

        BST<Integer> BST = new BST<>();

        for (int value : array) {
            BST.add(value);
        }
        BinaryTrees.print(BST);
        System.out.println("");
//        System.out.println(binarySearchTree.height2());
//        System.out.println(binarySearchTree.isComplete02());

        BinaryTree.Node<Integer> node = BST.rootNode.right.right;

        BinaryTree.Node<Integer> predecessor = BST.successor(node);
        if (predecessor != null) {
            System.out.println(predecessor.element);
        }else {
            System.out.println("null");
        }

    }
    private static void demo03() {

        int[] array = new int[] {1, 20, 10, 30, 12, 6, 32, 26, 3, 18, 11, 9};

        BST<Integer> BST = new BST<>();

        for (int value : array) {
            BST.add(value);
        }
        BinaryTrees.print(BST);
        System.out.println("");

        BST.remove(1);

        BinaryTrees.print(BST);
        System.out.println("");
    }


}
