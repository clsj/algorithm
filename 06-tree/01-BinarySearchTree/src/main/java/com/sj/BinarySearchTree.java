package com.sj;

import com.sj.printer.BinaryTreeInfo;
import com.sj.printer.BinaryTrees;

import javax.print.attribute.standard.NumberUp;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> implements TreeInterface<E>, BinaryTreeInfo {

    private int size;

    private Node<E> rootNode;

    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(E element) {

        checkElementCheck(element);

        Node<E> node = new Node<>(element);

        if (rootNode == null) {
            rootNode = node;
            size++;
            return;
        }
        Node<E> cur = rootNode;
        // 获取添加的元素
        while (true) {
            if (compare(cur.element, element) > 0) {
                // 左边
                if (cur.left == null) {
                    cur.left = node;
                    node.parent = cur;
                    break;
                }else {
                    cur = cur.left;
                }
                size++;
            }else if (compare(cur.element, element) < 0){
                // 右边
                if (cur.right == null) {
                    cur.right = node;
                    node.parent = cur;
                    break;
                }else {
                    cur = cur.right;
                }
                size++;
            }else {
                cur.element = element;
                return;
            }
        }

    }

    private int compare(E e1, E e2) {

        if (comparator != null) {
            return comparator.compare(e1, e2);
        }

        return ((Comparable<E>)e1).compareTo(e2);
    }

    @Override
    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }

        // 度为2
        if (node.hasTwoChildren()) {
            // 获取前驱节点
            Node<E> preNode = predecessor(node);
            // 将前驱节点的值赋予该节点
            node.element = preNode.element;
            // 前驱节点赋予node，删除前驱节点
            node = preNode;
        }

        // 删除node 度为1 或者0
        Node<E> replaceNode = node.left != null ? node.left : node.right;

        if (node.parent == null) {
            replaceNode.parent = null;
            rootNode = replaceNode;
        }else {
            // 度为0和1通用
            if (node.parent.left == node) {
                // node是左节点
                node.parent.left = replaceNode;
            }else {
                // node是右节点
                node.parent.right = replaceNode;
            }
        }

        size--;
    }

    public Node<E> node(E element) {

        Node<E> node = rootNode;

        while (node != null) {

            int compare = compare(element, node.element);

            if (compare == 0) {
                return node;
            }

            if (compare > 0) {
                node = node.right;
            }

            if (compare < 0) {
                node = node.left;
            }
        }

        return null;
    }


    @Override
    public boolean contains(E element) {
        return false;
    }

    private void checkElementCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        @Override
        public String toString() {
            String str = " ";
            if (parent != null) {
                str = parent.element.toString();
            }
            return element.toString() + " p:" +  str;
        }
    }


    // -----------------------------------------------------




    @Override
    public Object root() {
        return rootNode;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> node1 = (Node<E>) node;

        return node1.element;
    }

    public void demo01() {

        int[] array = new int[] {20, 10, 30, 12, 6};

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        for (int value : array) {
            binarySearchTree.add(value);
        }
        BinaryTrees.print(binarySearchTree);
        System.out.println("");
        binarySearchTree.preorderTraversal();
        binarySearchTree.inorderTraversal();
        binarySearchTree.postorderTraversal();
    }


    // ----------------------------遍历-------------------------

    /**
	 * 前序遍历
	 */
	public void preorderTraversal() {
        System.out.print("前序遍历 -> ");
		preorderTraversal(rootNode);
        System.out.println("");
	}

	private void preorderTraversal(Node<E> node) {
		if (node == null) return;

		System.out.print(node.element + "    ");
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}

    /**
     * 中序遍历
     */
    public void inorderTraversal() {
        System.out.print("中序遍历 -> ");
        inorderTraversal(rootNode);
        System.out.println("");
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.print(node.element + "    ");
        inorderTraversal(node.right);
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal() {
        System.out.print("后序遍历 -> ");
        postorderTraversal(rootNode);
        System.out.println("");
    }

    private void postorderTraversal(Node<E> node) {
        if (node == null) return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.element + "    ");
    }

    /**
     * 层序遍历
     * 1. 根节点入队列。
     * 2. 根节点出队列。
     * 3. 左子树，右子树依次入队列，循环至空。
     */
    public void levelOrderTraversal() {
        if (rootNode == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.print(node.element + "    ");

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        System.out.println("");
    }

    // =============================Visitor=============================

    public static abstract class Visitor<E> {
        boolean stop;
        /**
         * @return 如果返回true，就代表停止遍历
         */
        public abstract boolean visit(E element);
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(Visitor<E> visitor) {
        preorderTraversal(rootNode, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null || visitor.stop) return;

        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(Visitor<E> visitor) {
        inorderTraversal(rootNode, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {

        if (node == null || visitor == null || visitor.stop) return;

        inorderTraversal(node.left, visitor);
        if (!visitor.stop) {
            visitor.stop = visitor.visit(node.element);
        }

        inorderTraversal(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal(Visitor<E> visitor) {
        postorderTraversal(rootNode, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {

        if (node == null || visitor == null || visitor.stop) return;

        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        if (!visitor.stop) {
            visitor.stop = visitor.visit(node.element);
        }
    }

    /**
     * 层序遍历
     * 1. 根节点入队列。
     * 2. 根节点出队列。
     * 3. 左子树，右子树依次入队列，循环至空。
     */
    public void levelOrderTraversal(Visitor<E> visitor) {
        if (rootNode == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);

        while (!queue.isEmpty() && !visitor.stop) {
            Node<E> node = queue.poll();

            visitor.stop = visitor.visit(node.element);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }


    public static void demo02() {
        int[] array = new int[] {20, 10, 30, 12, 6};

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        for (int value : array) {
            binarySearchTree.add(value);
        }
        BinaryTrees.print(binarySearchTree);
        System.out.println("");
        System.out.print("前序遍历: ");
        binarySearchTree.preorderTraversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + "->");

                if (element == 12) return true;

                return false;
            }
        });
        System.out.println("");
        System.out.print("中序遍历: ");
        binarySearchTree.inorderTraversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + "->");
                if (element == 6) return true;
                return false;
            }
        });

        System.out.println("");
        System.out.print("后序遍历: ");
        binarySearchTree.postorderTraversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + "->");
                if (element == 30) return true;
                return false;
            }
        });
        System.out.println("");
        System.out.print("层序遍历: ");
        binarySearchTree.levelOrderTraversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + "->");
                if (element == 10) return true;
                return false;
            }
        });
    }
    // -----------------------------hight-----------------

    public int height() {
        return height(rootNode);
    }

    public int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int i = Math.max(height(node.left), height(node.right));
        return i + 1;
    }

    public int height2() {

        if (rootNode == null) return 0;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);

        int height = 0;
        // 表示每一层的size
        int leaveSize = 1;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            leaveSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            // 表示这一层访问完毕
            if (leaveSize == 0) {
                // 当一层访问完毕后，队列的size即为下一层的个数
                leaveSize = queue.size();
                height++;
            }
        }

        return height;
    }

    // 判断是否为完全二叉树
    // 层序遍历连续的二叉树
    public boolean isComplete() {

        if (rootNode == null) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);

        boolean flag = false;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 如果左右都不为空 依次加入队列
            if (node.left != null && node.right != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }

            if (node.left == null && node.right != null) {
                return false;
            }

            // 判断之后是否全为叶子节点
            if ((node.left != null && node.right == null) || node.isLeaf()) {
                flag = true;
            }

            if (flag) {
                Node<E> peek = queue.peek();
                if (peek != null && !peek.isLeaf()) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isComplete02() {

        if (rootNode == null) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);

        boolean flag = false;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (flag && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            }else {
                flag = true;
            }
        }
        return true;
    }

    // 前驱节点
    // left有值 left 右右右
    // left没值, 在左边的父节点
    public Node<E> predecessor(Node<E> node) {

        if (node == null) {
            return null;
        }

        Node<E> pred = node.left;

        if (pred != null) {
            while (pred.right != null) {
                pred = pred.right;
            }
            return pred;
        }

        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }

        return node.parent;
    }

    // 后继节点
    public Node<E> successor(Node<E> node) {

        if (node == null) {
            return null;
        }

        Node<E> suc = node.right;

        if (suc != null) {
            while (suc.left != null) {
                suc = suc.left;
            }
            return suc;
        }

        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }

        return node.parent;
    }


    public static void demo03() {
        int[] array = new int[] {20, 10, 30, 12, 6, 32, 26, 3, 18, 11, 9};
//        int[] array = new int[] {20, 10, 30};
//        int[] array = new int[] {20, 10, 30};

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        for (int value : array) {
            binarySearchTree.add(value);
        }
        BinaryTrees.print(binarySearchTree);
        System.out.println("");
//        System.out.println(binarySearchTree.height2());
//        System.out.println(binarySearchTree.isComplete02());

        Node<Integer> node = binarySearchTree.rootNode.right.right;

        Node<Integer> predecessor = binarySearchTree.successor(node);
        if (predecessor != null) {
            System.out.println(predecessor.element);
        }else {
            System.out.println("null");
        }

    }

    // ----------------------remove-------------

    private static void demo04() {

        int[] array = new int[] {1, 20, 10, 30, 12, 6, 32, 26, 3, 18, 11, 9};
        
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        for (int value : array) {
            binarySearchTree.add(value);
        }
        BinaryTrees.print(binarySearchTree);
        System.out.println("");

        binarySearchTree.remove(1);

        BinaryTrees.print(binarySearchTree);
        System.out.println("");
    }

    public static void main(String[] args) {
        demo04();
    }



}
